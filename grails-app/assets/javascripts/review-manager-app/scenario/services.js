'use strict';

function ScenarioResource(CrudResourceFactory) {
    return CrudResourceFactory('/api/scenario', 'Scenario');
}

angular.module('reviewManagerApp.scenario.services', ['grails'])
    .factory('ScenarioResource', ScenarioResource);
