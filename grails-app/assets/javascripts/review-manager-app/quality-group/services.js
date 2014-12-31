'use strict';

function QualityGroupResource(CrudResourceFactory) {
    return CrudResourceFactory('/api/qualityGroup', 'QualityGroup');
}

angular.module('reviewManagerApp.qualityGroup.services', ['grails'])
    .factory('QualityGroupResource', QualityGroupResource);
