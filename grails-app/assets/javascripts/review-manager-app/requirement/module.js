//= require_self 
//= require controllers
//= require services 
//= require /review-manager-app/review/services

//= require /review-manager-app/quality-group/services
//= require_tree /review-manager-app/requirement/templates/

'use strict';

angular.module('reviewManagerApp.requirement', [
	'grails', 
	'reviewManagerApp.requirement.controllers', 
	'reviewManagerApp.review.services',
	'reviewManagerApp.qualityGroup.services',
	'reviewManagerApp.requirement.services'
])
.value('defaultCrudResource', 'RequirementResource')
.config(function($routeProvider) {
	$routeProvider
        .when('/', {
            controller: 'ListCtrl as ctrl',
            templateUrl: 'list.html',
            resolve: {
                requirementList: function($route, RequirementResource) {
                    var params = $route.current.params;
                    return RequirementResource.list(params);
                },
				reviewList: function(ReviewResource) {
					return ReviewResource.list();
				}	
, 
				qualityGroupList: function(QualityGroupResource) {
					return QualityGroupResource.list();
				}	
 
            }
        })
        .when('/create', {
            controller: 'CreateEditCtrl as ctrl',
            templateUrl: 'create-edit.html',
            resolve: {
                requirement: function(RequirementResource) {
                    return RequirementResource.create();
                },
				reviewList: function(ReviewResource) {
					return ReviewResource.list();
				}	
, 
				qualityGroupList: function(QualityGroupResource) {
					return QualityGroupResource.list();
				}	
 
            }
        })
        .when('/edit/:id', {
            controller: 'CreateEditCtrl as ctrl',
            templateUrl: 'create-edit.html',
            resolve: {
                requirement: function($route, RequirementResource) {
                    var id = $route.current.params.id;
                    return RequirementResource.get(id);
                },
				reviewList: function(ReviewResource) {
					return ReviewResource.list();
				}	
, 
				qualityGroupList: function(QualityGroupResource) {
					return QualityGroupResource.list();
				}	
 
            }
        })
        .when('/show/:id', {
            controller: 'ShowCtrl as ctrl',
            templateUrl: 'show.html',
            resolve: {
                requirement: function($route, RequirementResource) {
                    var id = $route.current.params.id;
                    return RequirementResource.get(id);
                }
            }
        })
        .otherwise({redirectTo: '/'});
});
