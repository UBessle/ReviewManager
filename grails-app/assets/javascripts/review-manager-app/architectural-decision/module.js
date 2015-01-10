//= require_self 
//= require controllers
//= require services 
//= require_tree /review-manager-app/architectural-decision/templates/

'use strict';

angular.module('reviewManagerApp.architecturalDecision', [
	'grails', 
	'reviewManagerApp.architecturalDecision.controllers', 
	'reviewManagerApp.architecturalDecision.services'
])
.value('defaultCrudResource', 'ArchitecturalDecisionResource')
.config(function($routeProvider) {
	$routeProvider
        .when('/', {
            controller: 'ListCtrl as ctrl',
            templateUrl: 'list.html',
            resolve: {
                architecturalDecisionList: function($route, ArchitecturalDecisionResource) {
                    var params = $route.current.params;
                    return ArchitecturalDecisionResource.list(params);
                } 
            }
        })
        .when('/create', {
            controller: 'CreateEditCtrl as ctrl',
            templateUrl: 'create-edit.html',
            resolve: {
                architecturalDecision: function(ArchitecturalDecisionResource) {
                    return ArchitecturalDecisionResource.create();
                } 
            }
        })
        .when('/edit/:id', {
            controller: 'CreateEditCtrl as ctrl',
            templateUrl: 'create-edit.html',
            resolve: {
                architecturalDecision: function($route, ArchitecturalDecisionResource) {
                    var id = $route.current.params.id;
                    return ArchitecturalDecisionResource.get(id);
                } 
            }
        })
        .when('/show/:id', {
            controller: 'ShowCtrl as ctrl',
            templateUrl: 'show.html',
            resolve: {
                architecturalDecision: function($route, ArchitecturalDecisionResource) {
                    var id = $route.current.params.id;
                    return ArchitecturalDecisionResource.get(id);
                }
            }
        })
        .otherwise({redirectTo: '/'});
});
