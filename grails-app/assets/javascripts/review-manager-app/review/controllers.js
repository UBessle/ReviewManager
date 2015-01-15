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

function UploadCtrl($scope, ReviewResource, FileUploader) {
    var self = this

    var uploader = $scope.uploader = new FileUploader({
        url : ReviewResource.getUploadUrl()
    });

    // FILTERS
    uploader.filters.push({
        name: 'excelFilter',
        fn: function(item /*{File|FileLikeObject}*/, options) {
            var fileNameExtension = '|' + item.name.slice(item.name.lastIndexOf('.') + 1) + '|';
            console.info('excelFilter',fileNameExtension);
            return '|xls|xlsx|csv|'.indexOf(fileNameExtension) !== -1;
        }
    });

    // CALLBACKS

    uploader.onWhenAddingFileFailed = function(item /*{File|FileLikeObject}*/, filter, options) {
        console.info('onWhenAddingFileFailed', item, filter, options);
    };
    uploader.onAfterAddingFile = function(fileItem) {
        console.info('onAfterAddingFile', fileItem);
    };
    uploader.onAfterAddingAll = function(addedFileItems) {
        console.info('onAfterAddingAll', addedFileItems);
    };
    uploader.onBeforeUploadItem = function(item) {
        console.info('onBeforeUploadItem', item);
    };
    uploader.onProgressItem = function(fileItem, progress) {
        console.info('onProgressItem', fileItem, progress);
    };
    uploader.onProgressAll = function(progress) {
        console.info('onProgressAll', progress);
    };
    uploader.onSuccessItem = function(fileItem, response, status, headers) {
        console.info('onSuccessItem', fileItem, response, status, headers);
    };
    uploader.onErrorItem = function(fileItem, response, status, headers) {
        console.info('onErrorItem', fileItem, response, status, headers);
    };
    uploader.onCancelItem = function(fileItem, response, status, headers) {
        console.info('onCancelItem', fileItem, response, status, headers);
    };
    uploader.onCompleteItem = function(fileItem, response, status, headers) {
        console.info('onCompleteItem', fileItem, response, status, headers);
    };
    uploader.onCompleteAll = function() {
        console.info('onCompleteAll');
    };

    console.info('uploader', uploader);

}

angular.module('reviewManagerApp.review.controllers', [])
    .controller('ListCtrl', ListCtrl)
    .controller('ShowCtrl', ShowCtrl)
    .controller('CreateEditCtrl', CreateEditCtrl)
    .controller('UploadCtrl', UploadCtrl);