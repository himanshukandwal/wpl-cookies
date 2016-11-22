angular.module('user-profile', ['ui.router'])
    .component ('bids', {
        template: '<h2>User Bids</h2><ng-outlet></ng-outlet>',
        $routeConfig: [
            { path: '/',    name: 'BidList',   component: 'bidList', useAsDefault: true },
            { path: '/:id', name: 'BidDetail', component: 'bidDetail' }
        ]
    })
    .component ('bidList', {
        template:
        '<div ng-repeat="hero in $ctrl.heroes" ' +
        '     ng-class="{ selected: $ctrl.isSelected(hero) }">\n' +
        '<a ng-link="[\'HeroDetail\', {id: hero.id}]">{{hero.name}}</a>\n' +
        '</div>',
        controller: function () {

        }
    })
    .controller('registration', function($http,$window) {
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
            });
        }
    })
    .controller('login', function($http) {
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
                    self.result = false;
                }, function (response) {
                    console.log(response.data.status);
                    self.result = "invalid login or password!";
                });
            }
        }
    });