/**
 * Client script for cinema booking test task for IQBuzz
 * Written by Sergey Maksimenko
 */
console.log("test " + movieId);
/*global angular, console, WebSocket*/
(function () {
	"use strict";
	console.log("Client app started");
	var BACKEND = "/iqbuzztt",
		app = angular.module('cinemaBooking', ['angular-json-rpc']);

	app.controller('bookingController', ['$scope', '$http', function ($scope, $http) {
		console.log("bookingController");
		var movie,
			  places, //places data to display hall seat map
				ticketsToBook = [],
				rows = [],
				mName = 'test',
				CHEAPSEAT = 0,
				MIDDLESEAT = 1,
				LUXSEAT = 2,
				getPlaces = function (callback) {
					console.log("Getting places");
					$http.jsonrpc(BACKEND + '/PlaceService.json', 'getPlaces', [movieId]).
					success(function (data, status, headers, config) {
						places = data.result;
						callback();
					});
				},
				createMap = function () {
					places.forEach(function (place) {
						var row = rows[place.row];
						if(typeof row === 'undefined') {
							row = {};
							rows[place.row] = row;
						}
						row[place.seat] = place;
					});
				},
				selectSeat = function (seat) {
					if(seat.selected === "" || typeof seat.selected === 'undefined'){
						seat.selected = "seat_selected";
						ticketsToBook.push(seat);
					} else {
						var index = ticketsToBook.indexOf(seat);
						if (index >= 0) {
  						ticketsToBook.splice( index, 1 );
						}
						seat.selected = "";
					}
					console.log("Tickets to book length: " + ticketsToBook.length);
				},
				bookTickets = function (movieId) {
					console.log("Tickets to book length: " + ticketsToBook.length);
					//ticketsToBook = ticketsToBook.filter(function(val) { return val !== null; }).join(", ");
					if(ticketsToBook.length === 0){
						alert("Вы должны выбрать билеты, которые хотите забронировать");
						return;
					}
					for(var i = 0; i < ticketsToBook.length; i++) {
						delete ticketsToBook[i].mouseover;
						delete ticketsToBook[i].selected;
					}
					console.log("Booking tickets to " + $scope.userName);
					for(var i = 0; i < ticketsToBook.length; i++) {
						console.log(ticketsToBook[i].placeId);
					}
					$http.jsonrpc(BACKEND + '/TicketService.json', 'bookTicket', [movieId, $scope.userName, ticketsToBook]).
					success(function (data, status, headers, config) {
						console.log(data);
						var tickets = data.result;
						if(tickets.length === 0){
							$scope.wrongLuxSeatsAlert = true;
							setTimeout(function () {
								$scope.wrongLuxSeatsAlert = false;
								$scope.$apply();
							}, 5000);

						} else {
								getPlaces(createMap);
						}
					}).
					error(function (data, status, headers, config) {
						console.log(data);
						if(JSON.parse(data).error.code === -1){
							$scope.wrongLuxSeatsAlert = true;
							setTimeout(function () {
								$scope.wrongLuxSeatsAlert = false;
							}, 5000);
						}
					});
				},
				mouseover = function (seat) {
					console.log("Mouseover on seat " + seat.placeId);
					seat.mouseover = "seat_mouseover";
				},
				mouseleave = function (seat) {
					console.log("Mouseleave on seat " + seat.placeId);
					seat.mouseover = "";
				};

		getPlaces(createMap);
		$scope.mouseleave = mouseleave;
		$scope.mouseover = mouseover;
		$scope.selectSeat = selectSeat;
		$scope.bookTickets = bookTickets;
		$scope.movieName = mName;
		$scope.rows = rows;
	}]);

})();
