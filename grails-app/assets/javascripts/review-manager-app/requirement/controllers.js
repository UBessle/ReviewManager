'use strict';

function ListCtrl($scope, RequirementResource, requirementList, reviewList, qualityGroupList, pageSize) {
    var self = this;
    self.requirementList = requirementList;
	self.reviewList = reviewList;
self.qualityGroupList = qualityGroupList;
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

        RequirementResource.list(params).then(function(items) {
            self.requirementList = items;
        });
    };

    self.reload = function() {
        self.page = 1;
        self.load();
    }
}

function ShowCtrl(requirement) {
    var self = this;
    self.requirement = requirement;
};

function CreateEditCtrl(requirement, reviewList, qualityGroupList ) {
    var self = this;
	self.reviewList = reviewList;
    self.qualityGroupList = qualityGroupList;
    self.requirement = requirement;
}

angular.module('reviewManagerApp.requirement.controllers', [])
    .controller('ListCtrl', ListCtrl)
    .controller('ShowCtrl', ShowCtrl)
    .controller('CreateEditCtrl', CreateEditCtrl);