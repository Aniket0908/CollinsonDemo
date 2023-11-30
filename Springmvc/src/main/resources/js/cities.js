/**
 * 
 */
function getCities(){
    var name = $('#state').val();
 
    console.log(name);
    
    $.ajax({
        type: "GET",
        url : 'getCities',
        data: ({
            state: name
        }),
        success : function(data) {
        	console.log(data);
			$('#city').empty();
        	$('#city').append('<option value="">Select City</option>'); 
        	$.each(data, function(index, value) {
        		$('#city').append('<option value="' + value.id + '">' + value.name + '</option>');
        	});
        }
    });
}