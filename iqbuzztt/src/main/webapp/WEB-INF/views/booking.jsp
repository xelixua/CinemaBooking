<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<link rel="stylesheet" href="/iqbuzztt/resources/css/myStyles.css">
<script src="http://code.jquery.com/jquery-1.12.1.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.0/angular.min.js"></script>
<script type="text/javascript" src="/iqbuzztt/resources/js/angular-json-rpc.js"></script>
<script type="text/javascript">var movieId = ${movieId};</script>
<script type="text/javascript" src="/iqbuzztt/resources/js/client.js"></script>
<html ng-app="cinemaBooking">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8">
	<title>Бронирование билетов на фильм ${movieName}</title>
</head>
<body ng-controller="bookingController">
	<div class="header">
		<h2>Бронирование билетов на фильм ${movieName}</h2>
	</div>
	<div id="hall_map row">
		<!-- row -->
		<div class="hall_row row col-md-offset-3" ng-repeat="row in rows">
			<!-- seat -->
			<div class="{{seat.cssClass}} {{seat.mouseover}} {{seat.selected}} col-md-2" ng-repeat="seat in row" ng-click="selectSeat(seat)" ng-mouseover="mouseover(seat)" ng-mouseleave = "mouseleave(seat)">
			</div>
		</div>
	</div>
	<br>
	<div class="row">
		<div class="col-md-1 seat"></div>
		<div class="col-md-1">- обычные места</div>
		<div class="col-md-1 seat middle"></div>
		<div class="col-md-1">- Middle-места</div>
		<div class="col-md-1 seat lux"></div>
		<div class="col-md-1">- Lux-места</div>
		<div class="col-md-1 seat already_booked"></div>
		<div class="col-md-1">- заказанные места</div>
		<br><br>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<input class="form-control" placeholder="Ваше ФИО" type="text" ng-model="userName">
			</div>
			<button type="button" class="btn btn-default" ng-click="bookTickets(${movieId})">Заказать</button>
	</div>
</div>
<div class="row">
	<div ng-show="wrongLuxSeatsAlert" class="alert alert-danger col-md-3">
		Можно заказать только 2 соседних места категории Lux
	</div>
</div>
</body>
</html>
