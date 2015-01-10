'use strict';

function ArchitecturalDecisionResource(CrudResourceFactory) {
    return CrudResourceFactory('/api/architecturalDecision', 'ArchitecturalDecision');
}

angular.module('reviewManagerApp.architecturalDecision.services', ['grails'])
    .factory('ArchitecturalDecisionResource', ArchitecturalDecisionResource);
