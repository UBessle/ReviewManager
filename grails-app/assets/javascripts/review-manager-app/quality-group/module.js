//= require_self 
//= require controllers
//= require services 
//= require_tree /review-manager-app/quality-group/templates/

'use strict';

angular.module('reviewManagerApp.qualityGroup', [
	'grails', 
	'reviewManagerApp.qualityGroup.controllers', 
	'reviewManagerApp.qualityGroup.services'
])
.value('defaultCrudResource', 'QualityGroupResource')
.config(function($routeProvider) {
	$routeProvider
        .when('/', {
            controller: 'ListCtrl as ctrl',
            templateUrl: 'list.html',
            resolve: {
                qualityGroupList: function($route, QualityGroupResource) {
                    var params = $route.current.params;
                    return QualityGroupResource.list(params);
                } 
            }
        })
        .when('/create', {
            controller: 'CreateEditCtrl as ctrl',
            templateUrl: 'create-edit.html',
            resolve: {
                qualityGroup: function(QualityGroupResource) {
                    return QualityGroupResource.create();
                } 
            }
        })
        .when('/edit/:id', {
            controller: 'CreateEditCtrl as ctrl',
            templateUrl: 'create-edit.html',
            resolve: {
                qualityGroup: function($route, QualityGroupResource) {
                    var id = $route.current.params.id;
                    return QualityGroupResource.get(id);
                } 
            }
        })
        .when('/show/:id', {
            controller: 'ShowCtrl as ctrl',
            templateUrl: 'show.html',
            resolve: {
                qualityGroup: function($route, QualityGroupResource) {
                    var id = $route.current.params.id;
                    return QualityGroupResource.get(id);
                }
            }
        })
        .otherwise({redirectTo: '/'});
});
