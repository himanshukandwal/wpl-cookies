angular.module('biddingModule', ['ui.router', 'angular.filter', 'ngAnimate', 'smart-table'])
    .config (function ($stateProvider) {
        $stateProvider
            .state('find-address-post-bid', {
                url : '/user-profile/find-address-for-new-bid',
                params : { userInfo : null, token : null },
                templateUrl : '../../templates/user-profile/find-address.html',
                controller : 'find-address'
            })
            .state('user-post-bid', {
                url : '/user-profile/new-bid',
                templateUrl : '../../templates/user-profile/post-bid.html',
                params : { userInfo : null, address : null, token : null },
                controller : 'post-bid'
            })
            .state('user-show-all-bids', {
                url : '/user-profile/user-show-all-bids',
                templateUrl : '../../templates/user-profile/all-bids.html',
                params : { userInfo : null, token : null },
                controller : 'all-bids'
            })
            .state('user-show-my-bids', {
                url : '/user-profile/user-show-my-bids',
                templateUrl : '../../templates/user-profile/user-bids.html',
                params : { userInfo : null, token : null },
                controller : 'all-bids'
            })
            .state('user-show-bid-detail', {
                url : '/user-profile/user-show-bid-detail',
                templateUrl : '../../templates/user-profile/bid-detail.html',
                params : { userInfo : null, bid : null, token : null },
                controller : 'bid-detail'
            })
            .state('user-shopping-cart', {
                url : '/user-profile/user-shopping-cart',
                templateUrl : '../../templates/user-profile/shopping-cart.html',
                params : { userInfo : null, bid : null, token : null },
                controller : 'shopping-cart'
            })
            .state('user-checkout-success', {
                url : '/user-profile/user-checkout-success',
                templateUrl : '../../templates/user-profile/checkout-successful.html',
                params : { userInfo : null, bid : null, otherParty : null, token : null },
                controller : 'checkout-success'
            })
            .state('search-bids', {
                url : '/user-profile/search-bids',
                params : { userInfo : null, token : null },
                templateUrl : '../../templates/user-profile/bid-search.html',
                controller : 'search-bids'
            });

    })
    .controller('find-address', function ($http, $timeout, $stateParams, $state, $scope) {
        var self = this;
        self.userInfo = $stateParams.userInfo;
        self.token = $stateParams.token;

        self.newAddress = { countryCode : 'US' };

        self.states = [ 'AL' , 'AK' , 'AZ' , 'AR' , 'CA' , 'CO' , 'CT' , 'DE' , 'FL' , 'GA' , 'HI' , 'ID' , 'IL' , 'IN' , 'IA' , 'KS' , 'KY' , 'LA' ,
                        'ME' , 'MD' , 'MA' , 'MI' , 'MN' , 'MS' , 'MO' , 'MT' , 'NE' , 'NV' , 'NH' , 'NJ' , 'NM' , 'NY' , 'NC' , 'ND' , 'OH' , 'OK' ,
                        'OR' , 'PA' , 'RI' , 'SC' , 'SD' , 'TN' , 'TX' , 'UT' , 'VT' , 'VA' , 'WA' , 'WV' , 'WI' , 'WY' ];

        self.createAddress = function () {
            console.log(JSON.stringify(self.newAddress));
            $http.post('/api/address/' + self.token, self.newAddress).then(function (response) {
                self.message = false;
                $state.go('user-post-bid', { userInfo : self.userInfo, address : response.data.address, token : self.token });
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
        self.token = $stateParams.token;

        self.successfulPosting = false;

        self.apartmentTypes = [ '1 BHK', '2 BHK', '3 BHK', 'House' ];
        self.bid = { owner :  self.userInfo, addressEntity: self.address, activeInd: 'Y' };

        self.postBid = function () {
            self.bid.modifiedDate =  new Date().getTime();
            self.bid.hostedDate =  new Date().getTime();

            $http.post('/api/postBid/' + self.token, self.bid).then(function (response) {
                console.log(response.data.status);
                self.message = false;
                self.successfulPosting = true;

                var existing_bids = JSON.parse(localStorage.getItem('all-bids'));
                existing_bids[response.data.bid.bidId] = response.data.bid;
                localStorage.setItem('all-bids', JSON.stringify(existing_bids));

                $timeout(function() {
                    $state.go('user-profile', { userInfo : self.userInfo, token : self.token });
                }, 3000);

            }, function (response) {
                console.log(response.data.status);
                self.message = "error posting user !";
            });
        };

    })
    .controller('all-bids', function($http, $stateParams, $state, $timeout, $scope, Poller) {
        var self = this;
        self.userInfo = $stateParams.userInfo;
        self.token = $stateParams.token;

        $scope.rowCollection = Poller.data.collection;

        self.selectItem = function (bid) {
            $state.go('user-show-bid-detail', { userInfo : self.userInfo, bid : bid, token : self.token });
        };

        self.mySearch = function (bid) { return  (bid.owner.id == self.userInfo.id) ? true : false; };

    })
    .controller('bid-detail', function($http, $stateParams) {
        var self = this;
        self.userInfo = $stateParams.userInfo;
        self.token = $stateParams.token;
        self.bid = $stateParams.bid;

        self.transaction = { bid : self.bid, bidReceiver : self.userInfo, bidStatus : 'INTERESTED' };

        self.bidTransactions = [];

        self.isMyBid = self.bid.owner.id == self.userInfo.id;

        $http.get('/api/getTransactions/' + self.bid.bidId + '/' + self.token).then(function (response) {
            self.bidTransactions = response.data.transaction;
        }, function (response) {
            console.log(response.data);
        });

        self.selectTransaction = function (transaction) {
            delete transaction.$$hashKey;

            $http.put('/api/updateTransaction/FINALISED/' + self.token, transaction).then(function () {
                transaction.bidStatus = 'FINALISED';
                console.log('updated status successfully.');

            }, function (response) {
                console.log(response.data.status);
                self.message = "error updating user !";
            });
        };
        
        self.createTransactionItem = function () {
            self.message = false;

            $http.post('/api/createTransaction/' + self.token, self.transaction).then(function (response) {
                console.log(response.data.status);
                self.message = false;
                self.bidTransactions.push(response.data.transaction);

                delete self.transaction.comments;
                delete self.transaction.bidPrice;
                delete self.transaction.quantity;
            }, function (response) {
                console.log(response.data.status);
                self.message = "error publishing interest !";

                delete self.transaction.comments;
                delete self.transaction.bidPrice;
                delete self.transaction.quantity;
            });
        };

    })
    .controller('shopping-cart', function($http, $stateParams, $state) {
        var self = this;
        self.userInfo = $stateParams.userInfo;
        self.token = $stateParams.token;
        self.bid = $stateParams.bid;

        self.bidTransactions = [];

        $http.get('/api/getTransactions/' + self.bid.bidId + '/' + self.token).then(function (response) {
            self.bidTransactions = response.data.transaction;
        }, function (response) {
            console.log(response.data);
        });

        self.finalizedTransactionsSearch = function (transaction) {
            return  (transaction.bidStatus == 'FINALISED') ? true : false;
        };

        self.removeTransaction = function (transaction) {
            delete transaction.$$hashKey;

            $http.put('/api/updateTransaction/INTERESTED/' + self.token, transaction).then(function () {
                transaction.bidStatus = 'INTERESTED';
                console.log('updated status successfully.');

            }, function (response) {
                console.log(response.data.status);
                self.message = "error updating user !";
            });
        };

        self.checkout = function () {
            var checkoutBidTransactions = [];

            angular.forEach(self.bidTransactions, function (value, key) {
                if (value.bidStatus == "FINALISED")
                    checkoutBidTransactions.push(value);
            });

            $http.post('/api/checkoutCart/' + self.token, checkoutBidTransactions).then(function (response) {
                self.message = false;

                var otherParty = [];
                angular.forEach(response.data.transactions, function (value, key) {
                    otherParty.push(value.transactionInfo.bidReceiver.firstName + value.transactionInfo.bidReceiver.lastName);
                });

                var uniqueList = otherParty.filter(function(item, pos) {
                    return otherParty.indexOf(item) == pos;
                }).join(", ");

                console.log('checked-out successfully !');
                $state.go('user-checkout-success', { userInfo : self.userInfo, bid : self.bid , otherParty : uniqueList, token : self.token });
            }, function (response) {
                console.log(response.data.status);
                self.message = "error checkout items !";
            });
        };
    })
    .controller('checkout-success', function($stateParams, $timeout, $state) {
        var self = this;
        self.userInfo = $stateParams.userInfo;
        self.token = $stateParams.token;
        self.bid = $stateParams.bid;
        self.otherParty = $stateParams.otherParty;

        $timeout(function() {
            $state.go('user-show-my-bids', { userInfo : self.userInfo, token : self.token });
        }, 5000);
    })
    .controller('search-bids', function($http, $stateParams, $scope, $state, Poller) {
        var self = this;
        self.userInfo = $stateParams.userInfo;
        self.token = $stateParams.token;

        $scope.rowCollection = [];
        self.searchText = '';

        var completeCollection = Poller.data.collection;

        self.searchByFullTextSearch = function () {

            $scope.rowCollection = [];

            angular.forEach(completeCollection, function (value, key) {

                if (value.bidId.toString().toUpperCase() === self.searchText.toUpperCase()
                    || value.comments.toUpperCase().includes(self.searchText.toUpperCase())
                    || value.apartmentType.toUpperCase().includes(self.searchText.toUpperCase())
                    || value.price.toString().replace(/[.,\/#!$%\^&\*;:{}=\-_`~()]/g,"").includes(self.searchText)
                    || value.owner.firstName.includes(self.searchText)
                    || value.owner.lastName.toUpperCase().includes(self.searchText.toUpperCase())
                    || value.owner.email.toUpperCase().includes(self.searchText.toUpperCase())
                    || (value.owner.mobileNumber && value.owner.mobileNumber.toUpperCase().includes(self.searchText.toUpperCase())))

                    $scope.rowCollection.push(value);
            });

            console.log('got results : ' + $scope.rowCollection.length);
            self.tableShow = $scope.rowCollection.length > 0;
        };

        self.selectItem = function (bid) {
            $state.go('user-show-bid-detail', { userInfo : self.userInfo, bid : bid, token : self.token });
        };

    })
    .factory('Poller', function($http, $timeout) {
        var data = { collection: [] };

        var rowCollection = [];

        var poller = function() {

            if (localStorage.getItem('all-bids')) {
                console.log('getting delta bids !');

                var loadTime = 60000; //Load the data every second

                $http.get('/api/getBids/' + localStorage.getItem('all-bids-access-time') + '/Y29va2llcywxNDgxMzQzNjE0NTUx').then(function (response) {

                    var existing_bids = JSON.parse(localStorage.getItem('all-bids'));

                    if (response.data.bid.length > 0) {

                        // update existing data and add new one.
                        angular.forEach(response.data.bid, function (value, key) {
                            console.log('new data : [' + key + "] : [" + JSON.stringify(value) + "]");
                            existing_bids[value.bidId] = value;
                        });

                        localStorage.setItem('all-bids', JSON.stringify(existing_bids));
                        localStorage.setItem('all-bids-access-time', new Date().getTime());

                    }

                    rowCollection = [];

                    angular.forEach(existing_bids, function (value, key) {
                        rowCollection.push(value);
                    });

                    console.log("bids data size : " + rowCollection.length);

                    data.collection = rowCollection;

                }, function (response) {
                    console.log(response.data);
                });

            } else {
                console.log('getting all bids !');

                $http.get('/api/getBids/Y29va2llcywxNDgxMzQzNjE0NTUx').then(function (response) {
                    var existing_bids = {};
                    rowCollection = [];

                    localStorage.setItem('all-bids-access-time', new Date().getTime());

                    if (response.data.bid.length > 0) {

                        // update existing data and add new one.
                        angular.forEach(response.data.bid, function (value, key) {
                            existing_bids[value.bidId] = value;
                        });

                        angular.forEach(existing_bids, function (value, key) {
                            rowCollection.push(value);
                        });

                        data.collection = rowCollection;

                        localStorage.setItem('all-bids', JSON.stringify(existing_bids));
                    }
                }, function (response) {
                    console.log(response.data);
                });
            }

            $timeout(poller, loadTime);
        };
        poller();

        return {  data : data };
    });
