'use strict';

function ListCtrl($scope, ReviewResource, reviewList, pageSize) {
    var self = this;
    self.reviewList = reviewList;
	
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

        ReviewResource.list(params).then(function(items) {
            self.reviewList = items;
        });
    };

    self.reload = function() {
        self.page = 1;
        self.load();
    }
}

function ShowCtrl(review) {
    var self = this;
    self.review = review;
};

function CreateEditCtrl(review ) {
    var self = this;
	
    self.review = review;
}

angular.module('reviewManagerApp.review.controllers', [])
    .controller('ListCtrl', ListCtrl)
    .controller('ShowCtrl', ShowCtrl)
    .controller('CreateEditCtrl', CreateEditCtrl);