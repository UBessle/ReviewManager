//= require_self 
//= require controllers
//= require services 
//= require_tree /review-manager-app/review/templates/

'use strict';

angular.module('reviewManagerApp.review', [
	'grails', 
	'reviewManagerApp.review.controllers', 
	'reviewManagerApp.review.services'
])
.value('defaultCrudResource', 'ReviewResource')
.config(function($routeProvider) {
	$routeProvider
        .when('/', {
            controller: 'ListCtrl as ctrl',
            templateUrl: 'list.html',
            resolve: {
                reviewList: function($route, ReviewResource) {
                    var params = $route.current.params;
                    return ReviewResource.list(params);
                } 
            }
        })
        .when('/create', {
            controller: 'CreateEditCtrl as ctrl',
            templateUrl: 'create-edit.html',
            resolve: {
                review: function(ReviewResource) {
                    return ReviewResource.create();
                } 
            }
        })
        .when('/edit/:id', {
            controller: 'CreateEditCtrl as ctrl',
            templateUrl: 'create-edit.html',
            resolve: {
                review: function($route, ReviewResource) {
                    var id = $route.current.params.id;
                    return ReviewResource.get(id);
                } 
            }
        })
        .when('/show/:id', {
            controller: 'ShowCtrl as ctrl',
            templateUrl: 'show.html',
            resolve: {
                review: function($route, ReviewResource) {
                    var id = $route.current.params.id;
                    return ReviewResource.get(id);
                }
            }
        })
        .otherwise({redirectTo: '/'});
});
