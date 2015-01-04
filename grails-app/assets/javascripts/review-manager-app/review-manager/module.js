//= require_self 
//= require controllers
//= require services 
//= require_tree /review-manager-app/review-manager/templates/

'use strict';

angular.module('reviewManagerApp.reviewManager', [
	'grails', 
	'reviewManagerApp.reviewManager.controllers', 
	'reviewManagerApp.reviewManager.services'
])
.value('defaultCrudResource', 'ReviewManagerResource')
.config(function($routeProvider) {
	$routeProvider
        .when('/', {
            controller: 'ListCtrl as ctrl',
            templateUrl: 'list.html',
            resolve: {
                reviewManagerList: function($route, ReviewManagerResource) {
                    var params = $route.current.params;
                    return ReviewManagerResource.list(params);
                } 
            }
        })
        .when('/create', {
            controller: 'CreateEditCtrl as ctrl',
            templateUrl: 'create-edit.html',
            resolve: {
                reviewManager: function(ReviewManagerResource) {
                    return ReviewManagerResource.create();
                } 
            }
        })
        .when('/edit/:id', {
            controller: 'CreateEditCtrl as ctrl',
            templateUrl: 'create-edit.html',
            resolve: {
                reviewManager: function($route, ReviewManagerResource) {
                    var id = $route.current.params.id;
                    return ReviewManagerResource.get(id);
                } 
            }
        })
        .when('/show/:id', {
            controller: 'ShowCtrl as ctrl',
            templateUrl: 'show.html',
            resolve: {
                reviewManager: function($route, ReviewManagerResource) {
                    var id = $route.current.params.id;
                    return ReviewManagerResource.get(id);
                }
            }
        })
        .otherwise({redirectTo: '/'});
});
