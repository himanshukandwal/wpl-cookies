angular.module('loginModule', ['ui.router', 'ngAnimate'])
    .config (function ($stateProvider) {
        $stateProvider
            .state('loginsuccess', {
                url : '/user-profile',
                templateUrl : '../templates/user-profile.html'
            })
            .state('registrationsuccess', {
                url : '/reg-success',
                templateUrl : '../templates/reg-success.html'
            })
            .state('editprofile', {
                url : '/edit-profile',
                templateUrl : '../templates/edit-profile.html'

            })
            .state('contactsuccess', {
            url : '/thank-you',
            templateUrl : '../templates/thank-you.html'
            });
    })
    .controller('registration', function($http, $injector) {
        var self = this;

        self.submitDetails = function () {
            console.log(self.user);

            $http.get('/ping').then(function (response) {
                self.result = response.data.time;
                console.log('result :: ' + self.result)
            });

            $http.post('/createUser', self.user).then(function (response) {
                self.result = response.data.status;
                console.log('result :: ' + self.result)
                var $state = $injector.get('$state');
                $state.go('registrationsuccess');
            });
        }
    })
    .controller('contactus', function($http, $injector) {
        var self = this;

        self.submitContactDetails = function () {
            if (self.user == '' || self.user.email == '' || self.user.firstName == '') {
                if (self.user == '')
                    self.result = "null user element";
                else if (self.user.email == '')
                    self.result = "no user email provided";
                else
                    self.result = "no user name provided";
            } else {
                $http.post('/contactus', self.user).then(function (response) {

                    self.result = response.data.status;
                    console.log('result :: ' + self.result)
                    var $state = $injector.get('$state');
                    $state.go('contactsuccess');
                });
            }
        }
    })



    .controller('login', function($http, $injector) {
        var self = this;

        self.checkValidLogin = function () {
            if (self.user == '' || self.user.email == '' || self.user.password == '') {
                if (self.user == '')
                    self.result = "null user element";
                else if (self.user.email == '')
                    self.result = "no user email provided";
                else
                    self.result = "no user password provided";
            } else {
                $http.get('/checkLogin/' + self.user.email + "/" + self.user.password).then(function (response) {
                    console.log(response.data.status);
                    console.log(response.data.userInfo.firstName)
                    self.result = false;
                    sessionStorage.user= JSON.stringify(response.data.userInfo);
                    console.log(sessionStorage.user)

                    var $state = $injector.get('$state');
                    $state.go('loginsuccess');


                }, function (response) {
                    console.log(response.data.status);
                    self.result = "invalid login or password!";
                });
            }
        }
    });