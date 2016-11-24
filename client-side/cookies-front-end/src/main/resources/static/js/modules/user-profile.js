angular.module('userProfileModule', ['ui.router'])
    .config (function ($stateProvider) {
        $stateProvider
            .state('edit-profile', {
                url : '/edit-profile',
                params : { userInfo : null },
                templateUrl : '../../templates/user-profile/edit-profile.html',
            });
    })
    .controller('static-profile', function($stateParams) {
        this.userInfo = $stateParams.userInfo;
    })
    .controller('edit-profile', function($http, $state, $stateParams) {
        var self = this;
        self.userInfo = $stateParams.userInfo;

        // clone the original user information object.
        self.cleanCopy = jQuery.extend(true, {}, self.userInfo);
        
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
    // .component ('bids', {
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