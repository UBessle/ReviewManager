'use strict';

function RequirementResource(CrudResourceFactory) {
    return CrudResourceFactory('/api/requirement', 'Requirement');
}

angular.module('reviewManagerApp.requirement.services', ['grails'])
    .factory('RequirementResource', RequirementResource);
