angular.module('biddingModule', ['ui.router', 'angular.filter'])
    .config (function ($stateProvider) {
        $stateProvider
            .state('find-address-post-bid', {
                url : '/user-profile/find-address-for-new-bid',
                params : { userInfo : null },
                templateUrl : '../../templates/user-profile/find-address.html',
                controller : 'find-address'
            })
            .state('user-post-bid', {
                url : '/user-profile/new-bid',
                templateUrl : '../../templates/user-profile/post-bid.html',
                params : { userInfo : null, address : null },
                controller : 'post-bid'
            });
    })
    .controller('find-address', function ($http, $timeout, $stateParams, $state, $scope) {
        var self = this;
        self.userInfo = $stateParams.userInfo;

        if (localStorage.getItem('registered-addresses')) {
            console.log("retrieving from local storage");
            self.addresses = localStorage.getItem('registered-addresses');
        } else {
            console.log("retrieving from rest service");
            $http.get('/api/addresses').then(function (response) {
                self.addresses = response.data.addresses;
                console.log('got addresses : ' + self.addresses);
            }, function (response) {
                self.addresses = [];
                console.log('error getting addresses ' + response.data);
            });

            var tempFilterText = '',
                filterTextTimeout;

            $scope.$watch('searchText', function (val) {
                if (filterTextTimeout)
                    $timeout.cancel(filterTextTimeout);

                tempFilterText = val;
                filterTextTimeout = $timeout(function () {
                    $scope.filterText = tempFilterText;
                }, 250); // delay 250 ms
            });
            localStorage.setItem('registered-addresses', self.addresses);
        };

        self.newAddress = { countryCode : 'US' };

        self.states = [ 'AL' , 'AK' , 'AZ' , 'AR' , 'CA' , 'CO' , 'CT' , 'DE' , 'FL' , 'GA' , 'HI' , 'ID' , 'IL' , 'IN' , 'IA' , 'KS' , 'KY' , 'LA' ,
                        'ME' , 'MD' , 'MA' , 'MI' , 'MN' , 'MS' , 'MO' , 'MT' , 'NE' , 'NV' , 'NH' , 'NJ' , 'NM' , 'NY' , 'NC' , 'ND' , 'OH' , 'OK' ,
                        'OR' , 'PA' , 'RI' , 'SC' , 'SD' , 'TN' , 'TX' , 'UT' , 'VT' , 'VA' , 'WA' , 'WV' , 'WI' , 'WY' ];

        self.createAddress = function () {
            console.log(JSON.stringify(self.newAddress));
            $http.post('/api/address', self.newAddress).then(function (response) {
                self.message = false;
                $state.go('user-post-bid', { userInfo : self.userInfo, address : response.data.address });
            }, function (response) {
                console.log(response.data.status);
                self.message = "error posting user !";
            });
        };

        self.selectAddress = function (address) {
            console.log('selected address : ' + address);
        };
    })
    .controller('post-bid', function($http, $stateParams) {
        var self = this;
        self.userInfo = $stateParams.userInfo;
        self.address = $stateParams.address;

        self.apartmentTypes = [ '1 BHK', '2 BHK', '3 BHK', 'House' ];
        self.bid = { owner :  self.userInfo, addressEntity: self.address, hostedDate : new Date(), activeInd: 'Y' };

        self.postBid = function () {
            console.log(JSON.stringify(self.bid));
            $http.post('/api/postBid', self.bid).then(function (response) {
                console.log(response.data.status);
                self.message = false;
            }, function (response) {
                console.log(response.data.status);
                self.message = "error posting user !";
            });
        };

    });