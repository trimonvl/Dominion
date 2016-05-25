$(window).ready(function() {
		/**** RESIZE CARDS ****/
		function resizeCards() {
			var heightVictoryCards = $(".victoryCards .card").height();
			var heightKingdomCards = $(".kingdomCards .card").height();
			var heightTreasureCards = $(".treasureCards .card").height();
			var heightHandCards = $(".handCards .card").height();
			var heightPlayedCards = $(".playedCards .card").height();
			//var widthDiscardPileCards = $(".discardPile").width();
			var widthTrashPileCards = $(".trashPile").width();
			
			var widthVictoryCards = heightVictoryCards * 1.217;
			var widthKingdomCards = heightKingdomCards * 1.217;
			var widthTreasureCards = heightTreasureCards * 1.217;
			var widthHandCards = heightHandCards * 0.652;
			var widthPlayedCards = heightPlayedCards * 0.652;
			//var heightDiscardPileCards = widthDiscardPileCards * 1.533;
			var heightTrashPileCards = widthTrashPileCards * 1.533;
			
			$(".victoryCards .card, .treasureCards .card, .curseCards .card").css("width", widthVictoryCards);
			$(".kingdomCards .card").css("width", widthKingdomCards);
			$(".handCards .card").css("width", widthHandCards);
			$(".playedCards .card").css("width", widthPlayedCards);
			//$(".discardPile, .cardPile").css("height", heightDiscardPileCards);
			$(".trashPile").css("height", heightTrashPileCards);
		}
		
		/**** ROTATE CARDS ****/
		function rotateCards() {
			var numberHandCards = $(".handCards .card").length;
			
			if(numberHandCards >= 5) {
				var rotate = -15;
				var rotateInterval = 30 / (numberHandCards - 1);
			}
			
			else {
				if(numberHandCards == 1) {
					var rotate = 0;
				}
				
				else {
					var rotate = numberHandCards * -3;
					var rotateFullAngle = numberHandCards * 3 * 2;
					var rotateInterval = rotateFullAngle / (numberHandCards - 1);
				}
			}
			
			var handCards = document.getElementsByClassName("inHand");
			
			for (i = 0; i < numberHandCards; i++) {		
				handCards[i].style.transform = "rotate(" + rotate + "deg)"; 
				
				rotate += rotateInterval;
			}
			
			if (numberHandCards > 9) {
				var marginLeftHandCards = -100 - (numberHandCards * 5 / 2); 
				$(".handCards .card").css("margin-left", marginLeftHandCards);
				$(".handCards .card:first-child").css("margin-left", "0");
			}
		}
		
	    /**** GET PLAYERS ****/
	    function getPlayers() {
		    $.ajax({
				type: 'POST',
				data: {operation: "getPlayers"},
				url: 'AjaxController',
				success: function(result){
					$(".players").html(result);
				}
			});
	    }
	    
	    /**** GET VICTORY CARDS ****/
	    function getVictoryCards() {
		    $.ajax({
				type: 'POST',
				data: {operation: "getVictoryCards"},
				url: 'AjaxController',
				success: function(result){
					$(".victoryCards").html(result);
					resizeCards();
				}
			});
	    }
	    
	    /**** GET TREASURE CARDS ****/
	    function getTreasureCards() {
		    $.ajax({
				type: 'POST',
				data: {operation: "getTreasureCards"},
				url: 'AjaxController',
				success: function(result){
					$(".treasureCards").html(result);
					resizeCards();
				}
			});
	    }
	    
	    /**** GET CURSE CARDS ****/
	    function getCurseCards() {
		    $.ajax({
				type: 'POST',
				data: {operation: "getCurseCards"},
				url: 'AjaxController',
				success: function(result){
					$(".curseCards").html(result);
					resizeCards();
				}
			});
	    }
	    
	    /**** GET KINGDOM CARDS ****/
	    function getKingdomCards() {
		    $.ajax({
				type: 'POST',
				data: {operation: "getKingdomCards"},
				url: 'AjaxController',
				success: function(result){
					$(".kingdomCards").html(result);
					resizeCards();
				}
			});
	    }
	    
	    /**** GET PLAYER ON TURN ****/
	    function getPlayerOnTurn() {
		    $.ajax({
				type: 'POST',
				data: {operation: "getPlayerOnTurn"},
				url: 'AjaxController',
				success: function(result){
					$(".player").removeClass("onTurn");
					$(".player .piles").removeClass("hidden");
					
					$("div[name='" + result + "']").addClass("onTurn");
					$(".player .piles:not(div[name=" + result + "] .piles)").addClass("hidden");
				}
			});
	    }
	    
	    /**** GET TRASH ****/
	    function getTrash() {
		    $.ajax({
				type: 'POST',
				data: {operation: "getTrash"},
				url: 'AjaxController',
				success: function(result){
					$(".options .piles").html(result);
					resizeCards();
				}
			});
	    }
	    
		/**** DRAW ****/
	    function draw() {
	    	$.ajax({
				type: 'POST',
				data: {operation: "draw"},
				url: 'AjaxController',
				success: function(result){
					$(".handCards").html(result);
					resizeCards();
					rotateCards();
				}
			});
	    }
		
		/**** PLAY ****/
	    function play(card) {
	    	var cardtype = $(card).attr("cardtype");
	    	var waitingFor = "play";
	    	$.ajax({
				type: 'POST',
				data: {operation: "getWaitingFor"},
				url: 'AjaxController',
				success: function(result){
					waitingFor = result;
				}
			});
	    	$.ajax({
				type: 'POST',
				data: {operation: "getPhase"},
				url: 'AjaxController',
				success: function(result){
					var phase = result;
					console.log("Phase: " + phase);
					
					if((phase == 1 && cardtype == "Action" || cardtype == "Action - Attack") || waitingFor != "play") {
						console.log("Action( - Attack)!");
						playThisCard(card);
					}
						
					else if(phase == 2 && cardtype == "Treasure") {
						console.log("Treasure!");
					    playThisCard(card);
				    }
					
					function playThisCard() {
						var cardname = $(card).attr("cardname");
				    	var numberinhand = $(card).attr("numberinhand");
					    
					    $(card).appendTo(".playedCards");
					    $(card).removeClass("inHand");
					    $(card).addClass("played");
					    
					    //resizeCards();
					    //rotateCards();
						
						$.ajax({
							type: 'POST',
							data: {operation: "play",
								cardname: cardname,
								numberinhand: numberinhand},
							url: 'AjaxController',
							success: function(result){
								$("#result2").html(result);
								updateStats();
							}
						});
						
						draw();
					}
				}
			});
	    }
		
		/**** UPDATE STATS ****/
	    function updateStats() {
	    	$.ajax({
				type: 'POST',
				data: {operation: "updateStats"},
				url: 'AjaxController',
				success: function(result){
					$(".overviewTurn").html(result);
				}
			});
	    	
	    	$.ajax({
				type: 'POST',
				data: {operation: "getBuys"},
				url: 'AjaxController',
				success: function(result){
			    	if(result >= 1) {
				    	$.ajax({
							type: 'POST',
							data: {operation: "getCoins"},
							url: 'AjaxController',
							success: function(result){
						    	enableBuyableCards(result);
							}
						});
			    	}
			    	
			    	else {
			    		disableBuyableCards();
			    	}
				}
			});
	    }
		
		/**** ENABLE BUYABLE CARDS ****/
	    function enableBuyableCards(amountCoins) {
	    	for (i = 0; i < 17; i++) {
				var cost = $(".card[numberindiv=" + i + "]").attr("cost");
				
				if(amountCoins >= cost) {
					$(".card[numberindiv=" + i + "] .buy").removeClass("hidden");
				}
			}
		}
		
		/**** DISABLE BUYABLE CARDS ****/
	    function disableBuyableCards() {
			for (i = 0; i < 17; i++) {
				$(".card[numberindiv=" + i + "] .buy").addClass("hidden");
			}
		}
	    
	    /**** IS GAME OVER ****/
	    function isGameOver() {
	    	$.ajax({
				type: 'POST',
				data: {operation: "isGameOver"},
				url: 'AjaxController',
				success: function(result){
					console.log(result);
					if(result == "true"){
						window.location = "gameover.html";
					}
				}
			});
	    }
		
		/**** CLEARS ****/
		function clearPlayedCards() {
			$(".playedCards").html("<div class='overviewTurn'><h4>Actions</h4><div class='amountActions'>1</div><h4>Buys</h4><div class='amountBuys'>1</div><h4>Coins</h4><div class='amountCoins'>0</div></div><div class='actionButtons'><button type='button' class='' id='endActionPhase'>End action phase</button></div>");
		}
		
		function clearBuyableCards() {
			$(".kingdomCards .buy").addClass("hidden");
		}
		
		/**** SOUNDS ****/
		var backgroundSound = new Howl({
			urls: ['sounds/background-1.mp3'],
			loop: true
		});
		
		var optionButtonSound = new Howl({
			urls: ['sounds/click-1.wav'],
			  sprite: {
				    click: [0, 1000]
			  }
		});
	    
	    /********************************************************************************/
	    
	    $(window).resize(function() {
			resizeCards();
			rotateCards();
		});
	    
		$(".detailView").fancybox({
			openEffect	: 'elastic',
			closeEffect	: 'elastic',
		
			helpers : {
				title : {
					type : 'inside'
				}
			}
		});
	    
	    /**** NEW GAME BASIC GETTERS ****/
		var backgroundSoundOn = true;
		var optionButtonSoundOn = true;
		
		backgroundSound.play();
		isGameOver();
	    getPlayers();
	    getVictoryCards();
	    getTreasureCards();
	    getCurseCards();
	    getKingdomCards();
	    getPlayerOnTurn();
	    getTrash();
	    draw();
	    
	    /**** CLICKLISTENER OPTION BUTTON ****/
	    $(".options button").click(function() {
	    	optionButtonSound.play("click");
	    });
		
		/**** CLICKLISTENER FULLSCREEN ****/
	    $("#fullscreen").click(function() {
	    	var elem = document.getElementById('body');
	        if(document.webkitFullscreenElement) {
	            document.webkitCancelFullScreen();
	            $("#fullscreen div").css("background", "url(http://i.imgur.com/Sf79OKh.png) no-repeat");
				$("#fullscreen div").css("background-size", "cover");
	        }
	        
	        else {
	        	elem.webkitRequestFullScreen();
	        	$("#fullscreen div").css("background", "url(http://i.imgur.com/i6F5Aan.png) no-repeat");
				$("#fullscreen div").css("background-size", "cover");
	        };
		});
		
		/**** CLICKLISTENER MUTE BACKGROUND SOUND ****/
		$("#mutebgsound").click(function() {
			if(backgroundSoundOn == true) {
				backgroundSound.mute();
				optionButtonSound.mute();
				backgroundSoundOn = false;
				$("#mutebgsound div").css("background", "url(http://i.imgur.com/wRm2pT8.png) no-repeat");
				$("#mutebgsound div").css("background-size", "cover");
			}
			
			else {
				backgroundSound.unmute();
				optionButtonSound.unmute();
				backgroundSoundOn = true;
				$("#mutebgsound div").css("background", "url(http://i.imgur.com/UbY1dNO.png) no-repeat");
				$("#mutebgsound div").css("background-size", "cover");
			}
		});
	    
	    /**** CLICKLISTENER PLAY CARD ****/
	    $(".handCards").on("click", ".inHand", function() {
	    	play(this);
	    });
	    
	    /**** CLICKLISTENER PLAY ALL TREASURE CARDS ****/
	    $("body").on("click", "#playAllTreasureCards", function() {
	    	var numberCardsInHand = $(".inHand").length;
	    	for (i = 0; i < numberCardsInHand; i++) {
	    		var card = $(".inHand[numberinhand='" + i + "']");
	    		var cardtype = $(card).attr("cardtype");
	    		
	    		if(cardtype == "Treasure") {
	    			play(card);
	    		}
	    	}
	    });
	    
	    /**** CLICKLISTENER END ACTION PHASE ****/
		$("body").on("click", "#endActionPhase", function() {
		    	console.log("Click!");  	
		    	$.ajax({
					type: 'POST',
					data: {operation: "endPhase"},
					url: 'AjaxController',
					success: function(result){
						$(".actionButtons").html(result);
				}
			});
		});
	    
		/**** CLICKLISTENER END TREASURE PHASE ****/
	    $("body").on("click", "#endTreasurePhase", function() {
	    	$.ajax({
				type: 'POST',
				data: {operation: "endPhase"},
				url: 'AjaxController',
				success: function(result){
					$(".actionButtons").html(result);
				}
			});
	    });
	    
	    /**** CLICKLISTENER END TURN ****/
	    $("body").on("click", "#endTurn", function() {
	    	$.ajax({
				type: 'POST',
				data: {operation: "endTurn"},
				url: 'AjaxController',
				success: function(result){
					$(".actionButtons").html(result);
				}
			});
	    	
	   		isGameOver();
			getPlayers();
	    	getPlayerOnTurn();
	    	draw();
	    	clearPlayedCards();
	    	disableBuyableCards();
	    	getTrash();
	    });
		
	    /**** CLICKLISTENER BUY CARD ****/
	    $("body").on("click", ".card button", function() {
	    	var numberindiv = $(this).attr("numberindiv");
	    	
	    	$.ajax({
				type: 'POST',
				data: {operation: "buyCard",
					numberindiv: numberindiv},
				url: 'AjaxController'
			});
	    	
	    	updateStats();
	    	getVictoryCards();
	    	getTreasureCards();
	    	getKingdomCards();
	    });
	});