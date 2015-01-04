'use strict';

function ListCtrl($scope, ReviewManagerResource, reviewManagerList, pageSize) {
    var self = this;
    self.reviewManagerList = reviewManagerList;
	
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

        ReviewManagerResource.list(params).then(function(items) {
            self.reviewManagerList = items;
        });
    };

    self.reload = function() {
        self.page = 1;
        self.load();
    }
}

function ShowCtrl(reviewManager) {
    var self = this;
    self.reviewManager = reviewManager;
};

function CreateEditCtrl(reviewManager ) {
    var self = this;
	
    self.reviewManager = reviewManager;
}

angular.module('reviewManagerApp.reviewManager.controllers', [])
    .controller('ListCtrl', ListCtrl)
    .controller('ShowCtrl', ShowCtrl)
    .controller('CreateEditCtrl', CreateEditCtrl);