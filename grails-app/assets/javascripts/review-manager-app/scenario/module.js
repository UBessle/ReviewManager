//= require_self 
//= require controllers
//= require services 
//= require /review-manager-app/requirement/services
//= require_tree /review-manager-app/scenario/templates/

'use strict';

angular.module('reviewManagerApp.scenario', [
	'grails', 
	'reviewManagerApp.scenario.controllers', 
	'reviewManagerApp.requirement.services',
	'reviewManagerApp.scenario.services'
])
.value('defaultCrudResource', 'ScenarioResource')
.config(function($routeProvider) {
	$routeProvider
        .when('/', {
            controller: 'ListCtrl as ctrl',
            templateUrl: 'list.html',
            resolve: {
                scenarioList: function($route, ScenarioResource) {
                    var params = $route.current.params;
                    return ScenarioResource.list(params);
                },
				requirementList: function(RequirementResource) {
					return RequirementResource.list();
				}	
 
            }
        })
        .when('/create', {
            controller: 'CreateEditCtrl as ctrl',
            templateUrl: 'create-edit.html',
            resolve: {
                scenario: function(ScenarioResource) {
                    return ScenarioResource.create();
                },
				requirementList: function(RequirementResource) {
					return RequirementResource.list();
				}	
 
            }
        })
        .when('/edit/:id', {
            controller: 'CreateEditCtrl as ctrl',
            templateUrl: 'create-edit.html',
            resolve: {
                scenario: function($route, ScenarioResource) {
                    var id = $route.current.params.id;
                    return ScenarioResource.get(id);
                },
				requirementList: function(RequirementResource) {
					return RequirementResource.list();
				}	
 
            }
        })
        .when('/show/:id', {
            controller: 'ShowCtrl as ctrl',
            templateUrl: 'show.html',
            resolve: {
                scenario: function($route, ScenarioResource) {
                    var id = $route.current.params.id;
                    return ScenarioResource.get(id);
                }
            }
        })
        .otherwise({redirectTo: '/'});
});
