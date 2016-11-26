angular.module('userProfileModule', ['ui.router'])
    .config (function ($stateProvider) {
        $stateProvider
            .state('edit-profile', {
                url : '/edit-profile',
                params : { userInfo : null },
                templateUrl : '../../templates/user-profile/edit-profile.html',
            })
            .state('user-post-bid', {
                url : '/user-profile/new-bid',
                templateUrl : '../../templates/user-profile/post-bid.html',
                controller : 'static-profile'
            });
    })
    .controller('static-profile', function($http, $stateParams) {
        var self = this;
        self.userInfo = $stateParams.userInfo;
        self.apartmentTypes = [ '1 BHK', '2 BHK', '3 BHK', 'House' ];
        self.bid = { owner :  self.userInfo, hostedDate : new Date(), activeInd: 'Y', addressEntity : {
            id: 624,
            line1 : null,
            line2: null,
            countryCode : "US",
            zipCode : "982"
        } };


        self.postBid = function () {
            console.log(JSON.stringify(self.bid));
            $http.post('/api/postBid', self.bid).then(function () {
                console.log(response.data.status);
                self.message = false;
            }, function () {
                console.log(response.data.status);
                self.message = "error posting user !";
            });
        }

    })
    .controller('edit-profile', function($http, $state, $stateParams) {
        var self = this;
        self.userInfo = $stateParams.userInfo;

        // clone the original user information object.
        self.cleanCopy = jQuery.extend(true, {}, self.userInfo);

        self.gender = [ "male", "female" ];

        self.updateProfile = function () {
            $http.put('/api/updateUser', self.userInfo).then(function (response) {
                self.message = false;
                $state.go('user-profile', { userInfo : response.data.userInfo });
            }, function (response) {
                console.log(response.data.status);
                self.message = "error updating user !";
            });
        };

        self.goBackToProfile = function () {
            $http.get('/api/checkLogin/' + self.cleanCopy.email + "/" + self.cleanCopy.password).then(function (response) {
                $state.go('user-profile', { userInfo : response.data.userInfo });
            }, function (response) {
                console.log('going back with potential unsaved changes. ' + response.data.status);
                $state.go('user-profile', { userInfo : self.userInfo });
            });
        };

    });