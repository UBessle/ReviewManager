'use strict';

function ListCtrl($scope, QualityGroupResource, qualityGroupList, pageSize) {
    var self = this;
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

        QualityGroupResource.list(params).then(function(items) {
            self.qualityGroupList = items;
        });
    };

    self.reload = function() {
        self.page = 1;
        self.load();
    }
}

function ShowCtrl(qualityGroup) {
    var self = this;
    self.qualityGroup = qualityGroup;
};

function CreateEditCtrl(qualityGroup ) {
    var self = this;
	
    self.qualityGroup = qualityGroup;
}

angular.module('reviewManagerApp.qualityGroup.controllers', [])
    .controller('ListCtrl', ListCtrl)
    .controller('ShowCtrl', ShowCtrl)
    .controller('CreateEditCtrl', CreateEditCtrl);