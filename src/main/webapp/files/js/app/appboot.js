var appData = {};
var appStarter = {
		init: function(){
			this.makeButton(1, "btn_admin", "ADMINISTRACE").click(function(){
				event.preventDefault();
				alert("admin");
			});
			this.makeButton(1, "btn_sklad", "SKLAD");
			this.makeButton(1, "btn_stats", "STATISTIKY");

			this.makeButton(2, "btn_users", "Users");
			
			this.makeButton(3, "btn_create_user", "Create Cat").click(function(event){
				event.preventDefault();			
				$.ajax({
				    url: "/bar/json/item_category/create/",
				    type: 'GET',
				    dataType: 'json',
				    data: "{}",
				    contentType: 'application/json', mimeType: 'application/json',
				    success: function(data) {
						appData.cat = data;
				    	$("#testpane").empty().append("Received data:<br/>"+data);
						$("#testpane").empty().append("Received data:<br/>"+"<textarea>"+JSON.stringify(data, null, 4)+"</textarea>");
				    },
				    error:function(data,status,er) {
				        $("#testpane").empty().append("Error occured :(<br/> data:<br/>"+data+"<br/> status:<br/>"+status+"<br/> er:<br/>"+er);
				    }
				});
			});
			this.makeButton(3, "btn_read_user", "Read Cat").click(function(event){
				event.preventDefault();			
				$.ajax({
				    url: "/bar/json/item_category/3",
				    type: 'GET',
				    dataType: 'json',
				    data: "{}",
				    contentType: 'application/json', mimeType: 'application/json',
				    success: function(data) {
				    	$("#testpane").empty().append("Received data:<br/>"+data);
						$("#testpane").empty().append("Received data:<br/>"+"<textarea>"+JSON.stringify(data, null, 4)+"</textarea>");
				    },
				    error:function(data,status,er) {
				        $("#testpane").empty().append("Error occured :(<br/> data:<br/>"+data+"<br/> status:<br/>"+status+"<br/> er:<br/>"+er);
				    }
				});
			});
			this.makeButton(3, "btn_update_user", "Update Cat").click(function(event){
				event.preventDefault();	
				appData.cat.name = "MickeyMouse";
				console.log("user:"+JSON.stringify(appData.cat));
				$.ajax({
				    url: "/bar/json/item_category/update/"+appData.cat.id,
				    type: 'POST',
				    dataType: 'json',
				    data: JSON.stringify(appData.cat),
				    contentType: 'application/json', mimeType: 'application/json',
					//contentType: 'application/x-www-form-urlencoded', mimeType: 'application/x-www-form-urlencoded',
				    success: function(data) {
				    	$("#testpane").empty().append("Received data:<br/>"+data);
						$("#testpane").empty().append("Received data:<br/>"+"<textarea>"+JSON.stringify(data, null, 4)+"</textarea>");
				    },
				    error:function(data,status,er) {
				        $("#testpane").empty().append("Error occured :(<br/> data:<br/>"+data+"<br/> status:<br/>"+status+"<br/> er:<br/>"+er);
				    }
				});
			});;
			this.makeButton(3, "btn_delete_user", "Delete Cat");
			
			$("<div/>", {
			    id: 'testpane',
			    text: 'Hello, imma new!'
			}).appendTo("#mainview");
		},

		makeButton: function(lvl, id, text){
			console.log("btn:"+lvl+"|"+id+"|"+text);
			var mydiv = null;
			if(lvl == 1){
				/*if($("#upmenu").length == 0){$("<div/>", {id:'upmenu'}).appendTo("#centerframe");}*/
				mydiv = $("#upmenu");
			}
			if(lvl == 2){
				/*if($("#secmenu").length == 0){$("<div/>", {id:'secmenu'}).appendTo("#centerframe");}*/
				mydiv = $("#secmenu");
			}
			if(lvl > 2){
				var myDivName = "menulvl"+lvl;
				if($("#"+myDivName).length == 0){$("<div/>", {id:myDivName, class:"smallmenu"}).appendTo("#mainview");}
				mydiv = $("#"+myDivName);
			}
			var btn = $("<a/>", {id: id, href: "#", text: text}).appendTo(mydiv);
			return btn.button();
		}
		
		
};

$(function() {
	appStarter.init();
});