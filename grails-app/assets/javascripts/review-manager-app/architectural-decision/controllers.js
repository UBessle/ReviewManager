'use strict';

function ListCtrl($scope, ArchitecturalDecisionResource, architecturalDecisionList, pageSize) {
    var self = this;
    self.architecturalDecisionList = architecturalDecisionList;
	
    self.pageSize = pageSize;
    self.page = 1;
    self.filter = {};

    $scope.$watchCollection(function() { return self.filter }, function() {
        self.reload();
    });

    self.load = function() {
        var params = {page: self.page};

        if (self.sort) {
            angular.extend(params, self.sort);
        }
		if (self.filter) {
			params.filter = self.filter
		}

        ArchitecturalDecisionResource.list(params).then(function(items) {
            self.architecturalDecisionList = items;
        });
    };

    self.reload = function() {
        self.page = 1;
        self.load();
    }
}

function ShowCtrl(architecturalDecision) {
    var self = this;
    self.architecturalDecision = architecturalDecision;
};

function CreateEditCtrl(architecturalDecision ) {
    var self = this;
	
    self.architecturalDecision = architecturalDecision;
}

angular.module('reviewManagerApp.architecturalDecision.controllers', [])
    .controller('ListCtrl', ListCtrl)
    .controller('ShowCtrl', ShowCtrl)
    .controller('CreateEditCtrl', CreateEditCtrl);