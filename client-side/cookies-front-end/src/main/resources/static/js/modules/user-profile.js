angular.module('userProfileModule', ['ui.router', 'biddingModule'])
    .config (function ($stateProvider) {
        $stateProvider
            .state('edit-profile', {
                url : '/user-profile/edit',
                params : { userInfo : null, token : null },
                templateUrl : '../../templates/user-profile/edit-profile.html',
            });
    })
    .controller('static-profile', function($http, $stateParams) {
        var self = this;
        self.userInfo = $stateParams.userInfo;
        self.token = $stateParams.token;

        angular.module('biddingModule').run(function(Poller) {});
    })
    .controller('edit-profile', function($http, $state, $stateParams) {
        var self = this;
        self.userInfo = $stateParams.userInfo;
        self.token = $stateParams.token;

        // clone the original user information object.
        self.cleanCopy = jQuery.extend(true, {}, self.userInfo);

        self.gender = [ "male", "female" ];

        self.updateProfile = function () {
            $http.put('/api/updateUser', self.userInfo).then(function (response) {
                self.message = false;
                $state.go('user-profile', { userInfo : response.data.userInfo, token : self.token });
            }, function (response) {
                console.log(response.data.status);
                self.message = "error updating user !";
            });
        };

        self.goBackToProfile = function () {
            $http.get('/api/checkLogin/' + self.cleanCopy.email + "/" + self.cleanCopy.password).then(function (response) {
                $state.go('user-profile', { userInfo : response.data.userInfo, token : self.token });
            }, function (response) {
                console.log('going back with potential unsaved changes. ' + response.data.status);
                $state.go('user-profile', { userInfo : self.userInfo, token : self.token });
            });
        };

    });