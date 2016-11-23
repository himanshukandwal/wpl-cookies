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
    .controller('edit-profile', function($http, $stateParams) {
        var self = this;
        self.userInfo = $stateParams.userInfo;
        console.log(JSON.stringify(self.userInfo));
        
        self.updateProfile = function () {
            $http.post('/contactus', self.user).then(function (response) {
                self.message = false;
                console.log('status :: ' + response.data.status)
                var $state = $injector.get('$state');
                $state.go('contact-success');
            });
        };
    });
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