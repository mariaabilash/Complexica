var app = angular.module('locations', [])
app.controller('locationscontroller', function($scope, $http) {
    $http.get('http://localhost:8080/getLocationsFromCSV').
        then(function(response) {
            $scope.locationscsv = response.data;
        });
    $scope.url = 'http://localhost:8080';
    $scope.postDiv = false;
    $scope.getDiv = false;
    $scope.showPostDiv = function() {
        $http({
            method : "POST",
            url : $scope.url + "/post",
            data : JSON.stringify($scope.locationscsv),
            headers : {
                'Content-Type' : 'application/json'
            }
        }).
        then(
            function(response) {
                $scope.post.response = "Sucessfully posted. Response status is " + response.status;
                $scope.postDiv = true;
            },
            function(error) {
                $scope.post.response = "Failed. Response status is " + error.status;
                $scope.postDiv = true;
        });
        $scope.getDiv = false;
    }
    $scope.showGetDiv = function() {
        $scope.postDiv = false;
        $http.get($scope.url + '/get').
        then(
            function(response) {
                $scope.locations = response.data;
                $scope.getDiv = true;
            },
            function(error) {
            	$scope.getDiv = false;
            });
    }
});
