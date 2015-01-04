'use strict';

function ListCtrl($scope, ScenarioResource, scenarioList, requirementList, pageSize) {
    var self = this;
    self.scenarioList = scenarioList;
	self.requirementList = requirementList;
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

        ScenarioResource.list(params).then(function(items) {
            self.scenarioList = items;
        });
    };

    self.reload = function() {
        self.page = 1;
        self.load();
    }
}

function ShowCtrl(scenario) {
    var self = this;
    self.scenario = scenario;
};

function CreateEditCtrl(scenario, requirementList ) {
    var self = this;
	self.requirementList = requirementList;
    self.scenario = scenario;
}

angular.module('reviewManagerApp.scenario.controllers', [])
    .controller('ListCtrl', ListCtrl)
    .controller('ShowCtrl', ShowCtrl)
    .controller('CreateEditCtrl', CreateEditCtrl);