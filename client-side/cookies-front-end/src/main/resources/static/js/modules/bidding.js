angular.module('biddingModule', ['ui.router', 'angular.filter', 'ngAnimate', 'smart-table'])
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
            })
            .state('user-show-all-bids', {
                url : '/user-profile/user-show-all-bids',
                templateUrl : '../../templates/user-profile/all-bids.html',
                params : { userInfo : null },
                controller : 'all-bids'
            })
            .state('user-show-my-bids', {
                url : '/user-profile/user-show-my-bids',
                templateUrl : '../../templates/user-profile/user-bids.html',
                params : { userInfo : null },
                controller : 'all-bids'
            })
            .state('user-show-bid-detail', {
                url : '/user-profile/user-show-bid-detail',
                templateUrl : '../../templates/user-profile/bid-detail.html',
                params : { userInfo : null, bid : null },
                controller : 'bid-detail'
            })
            .state('user-shopping-cart', {
                url : '/user-profile/user-shopping-cart',
                templateUrl : '../../templates/user-profile/shopping-cart.html',
                params : { userInfo : null },
                controller : 'shopping-cart'
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
    .controller('post-bid', function($http, $stateParams, $state, $timeout) {
        var self = this;
        self.userInfo = $stateParams.userInfo;
        self.address = $stateParams.address;

        self.successfulPosting = false;

        self.apartmentTypes = [ '1 BHK', '2 BHK', '3 BHK', 'House' ];
        self.bid = { owner :  self.userInfo, addressEntity: self.address, hostedDate : new Date(), activeInd: 'Y' };

        self.postBid = function () {

            $http.post('/api/postBid', self.bid).then(function (response) {
                console.log(response.data.status);
                self.message = false;
                self.successfulPosting = true;

                $timeout(function() {
                    $state.go('user-profile', { userInfo : self.userInfo });
                }, 3000);

            }, function (response) {
                console.log(response.data.status);
                self.message = "error posting user !";
            });
        };

    })
    .controller('all-bids', function($http, $stateParams, $state, $timeout, $scope) {
        var self = this;
        self.userInfo = $stateParams.userInfo;

        $scope.rowCollection = [];

        if (localStorage.getItem('bids')) {

        } else {
            $http.get('/api/getBids').then(function (response) {
                $scope.rowCollection = response.data.bid;
            }, function (response) {
                console.log(response.data);
            });
        }

        self.selectItem = function (bid) {
            $state.go('user-show-bid-detail', { userInfo : self.userInfo, bid : bid });
        };

        $scope.mySearch = function (bid) { return  (bid.owner.id == self.userInfo.id) ? true : false; };

    })
    .controller('bid-detail', function($http, $stateParams) {
        var self = this;
        self.userInfo = $stateParams.userInfo;
        self.bid = $stateParams.bid;

        self.transaction = { bid : self.bid, bidReceiver : self.userInfo, bidStatus : 'INTERESTED' };

        self.bidTransactions = [];

        self.isMyBid = self.bid.owner.id == self.userInfo.id;

        $http.get('/api/getTransactions/' + self.bid.bidId).then(function (response) {
            self.bidTransactions = response.data.transaction;
        }, function (response) {
            console.log(response.data);
        });

        self.selectTransaction = function (transaction) {
            delete transaction.$$hashKey;

            $http.put('/api/updateTransaction/FINALISED', transaction).then(function () {
                transaction.bidStatus = 'FINALISED';
                console.log('updated status successfully.');

            }, function (response) {
                console.log(response.data.status);
                self.message = "error updating user !";
            });
        };
        
        self.createTransactionItem = function () {
            self.message = false;

            $http.post('/api/createTransaction', self.transaction).then(function (response) {
                console.log(response.data.status);
                self.message = false;
                self.bidTransactions.push(response.data.transaction);

                delete self.transaction.comments;
                delete self.transaction.bidPrice;
            }, function (response) {
                console.log(response.data.status);
                self.message = "error publishing interest !";

                delete self.transaction.comments;
                delete self.transaction.bidPrice;
            });
        };

    })
    .controller('shopping-cart', function($http, $stateParams) {
        var self = this;
        self.userInfo = $stateParams.userInfo;

        self.transaction = { bid : self.bid, bidReceiver : self.userInfo, bidStatus : 'INTERESTED' };

        self.bidTransactions = [];

        self.isMyBid = self.bid.owner.id == self.userInfo.id;

        $http.get('/api/getTransactions/' + self.bid.bidId).then(function (response) {
            self.bidTransactions = response.data.transaction;
        }, function (response) {
            console.log(response.data);
        });

        self.selectTransaction = function (transaction) {
            delete transaction.$$hashKey;

            $http.put('/api/updateTransaction/FINALISED', transaction).then(function () {
                transaction.bidStatus = 'FINALISED';
                console.log('updated status successfully.');

            }, function (response) {
                console.log(response.data.status);
                self.message = "error updating user !";
            });
        };

        self.createTransactionItem = function () {
            self.message = false;

            $http.post('/api/createTransaction', self.transaction).then(function (response) {
                console.log(response.data.status);
                self.message = false;
                self.bidTransactions.push(response.data.transaction);

                delete self.transaction.comments;
                delete self.transaction.bidPrice;
            }, function (response) {
                console.log(response.data.status);
                self.message = "error publishing interest !";

                delete self.transaction.comments;
                delete self.transaction.bidPrice;
            });
        };

    });