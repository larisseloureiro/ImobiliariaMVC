$('#estado').change(function(){
	let municipio = $('#municipio')
	municipio.empty();
	
	const url = '/home/bairro/listar?codigoEstado=' + $("#estado").val();
	
	$.getJSON(url, function(data){
		
		$.each(data, function(key, object){
			municipio.append($('<option></option>').attr('value', object.codigo).text(object.nomeMunicipio));
		})
	});
	
});