<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<Html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <Title> FileDirectory Parser</Title>
           
  </head>
  <Body>
    <H4>
      FileDirectory Parser Page
    </H4>
    <Label>Directory Path : <input id='dirpath' value=""></Label>
    <Label> File Path : <input id='filepath' value=""></Label>
  
     <script>
        var sel = document.getElementById("dirpath"), text = document.getElementById("filepath");

	sel.onchange = function(e) {
  text.disabled = (sel.value !== "");
};
	text.onchange=function(e)
	{
		 sel.disabled = (text.value !== "");
		 
	};
      </script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <p>
      <Button id="btnParse">
        Parse
      </Button>      
    </p>
    <script>
    $('#btnParse').click(function() {
    	var requestbody="";    
    		if(text.value !== "")
    			{
    			requestbody=document.getElementById("filepath").value;     		
        		getfileData(requestbody);
    			}
    		else
    			{
    			requestbody=document.getElementById("dirpath").value; 
    			getDirData(requestbody);
    			}
    		
    		
    	
    	
    });

    function getDirData(data){// pass your data in method
    	
        $.ajax({        	
                type: "POST",
                url: "http://localhost:8585/controller/getdir",
                data: JSON.stringify(data),// now data come in this function
                contentType: "application/json; charset=utf-8",
                crossDomain: true,
                dataType: "json",
                success: function (response) {                	
                
                    //alert(JSON.stringify(response));// write success in " "
                    document.getElementById("lblResult").innerHTML=JSON.stringify(response,null, 2);
                },

                error: function (response, status) {
                    // error handler
                    console.log(response);
                    alert('fail' + JSON.stringify(response,null, 2));
                }
             });
       }
    
function getfileData(data){// pass your data in method
    	
        $.ajax({        	
                type: "POST",
                url: "http://localhost:8585/controller/getfile",
                data: JSON.stringify(data),// now data come in this function
                contentType: "application/json; charset=utf-8",
                crossDomain: true,
                dataType: "json",
                success: function (response) {                	
                
                    //alert(JSON.stringify(response));// write success in " "
                    document.getElementById("lblResult").innerHTML=JSON.stringify(response,null, 2);
                },

                error: function (jqXHR, status) {
                    // error handler
                    console.log(jqXHR);
                    alert('fail' + status.code);
                }
             });
       }

</script>
   
    
    <div>
      <label>-----------------------------Click Parse To View Result Below-------------------------------------------</label>
      <p>
        
        <textarea id='lblResult'  rows="20" cols="100"></textarea>
      </p>
     
    </div>
  </Body>
  
</Html>