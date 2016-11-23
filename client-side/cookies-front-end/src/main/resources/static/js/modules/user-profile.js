angular.module('user-profile', ['ui.router'])
    .config (function ($stateProvider) {
        $stateProvider
            .state('edit-profile', {
                url : '/edit-profile',
                params : { userInfo : null },
                templateUrl : '../../templates/edit-profile.html',
            });
    })
    .controller('static-profile', function($stateParams) {
        this.userInfo = $stateParams.userInfo;
    })
    .controller('edit-profile', function($http) {
        var self = this;

        self.getProfile = function () {

            $http.get('/ping').then(function (response) {
                self.result = response.data.time;
                console.log('result :: ' + self.result)
            });

            if (self.user.passwordConfirm != self.user.password) {
                self.message = "confirmation password does not matches password";
            } else {
                $http.post('/createUser', self.user).then(function (response) {
                    self.message = false;
                    var $state = $injector.get('$state');
                    $state.go('registration-success');
                }, function (response) {
                    console.log(response.data.status);
                    self.message = "error creating user !";
                });
            }
        }
    })
    //  .component ('bids', {
    //     template: '<h2>User Bids</h2><ng-outlet></ng-outlet>',
    //     $routeConfig: [
    //         { path: '/',  name: 'BidList',   component: 'bidList', useAsDefault: true },
    //         { path: '/:id', name: 'BidDetail', component: 'bidDetail' }
    //     ]
    // })
    // .component ('bidList', {
    //     template:
    //     '<div ng-repeat="hero in $ctrl.heroes" ' +
    //     '     ng-class="{ selected: $ctrl.isSelected(hero) }">\n' +
    //     '<a ng-link="[\'HeroDetail\', {id: hero.id}]">{{hero.name}}</a>\n' +
    //     '</div>',
    //     controller: function () {
    //
    //     }
    // });