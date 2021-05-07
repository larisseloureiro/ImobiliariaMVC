$('#confirmacaoExclusaoCategorias').on('show.bs.modal', function(event) {

	var button = $(event.relatedTarget);

	var codigoCategoria = button.data('codigo');
	var tipoCategoria = button.data('tipo');

	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');

	if (!action.endsWith('/')) {
		action += '/';
	}

	form.attr('action', action + codigoCategoria);

	modal.find('.modal-body span').html('Tem certeza que deseja excluir a categoria <strong>' + tipoCategoria + '</strong>?');
});

$('#confirmacaoExclusaoNegocio').on('show.bs.modal', function(event) {

	var button = $(event.relatedTarget);

	var codigoNegocio = button.data('codigo');
	var tipoNegocio = button.data('tipo');

	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');

	if (!action.endsWith('/')) {
		action += '/';
	}

	form.attr('action', action + codigoNegocio);

	modal.find('.modal-body span').html('Tem certeza que deseja excluir o negócio <strong>' + tipoNegocio + '</strong>?');
});

$('#confirmacaoExclusaoQuarto').on('show.bs.modal', function(event) {

	var button = $(event.relatedTarget);

	var codigoQuarto = button.data('codigo');
	var qntdQuarto = button.data('quarto');

	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');

	if (!action.endsWith('/')) {
		action += '/';
	}

	form.attr('action', action + codigoQuarto);

	modal.find('.modal-body span').html('Tem certeza que deseja excluir o quarto <strong>' + qntdQuarto + '</strong>?');
});



$('#confirmacaoExclusaoEstado').on('show.bs.modal', function(event) {

	var button = $(event.relatedTarget);

	var codigoEstado = button.data('codigo');
	var state = button.data('state');
	

	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');

	if (!action.endsWith('/')) {
		action += '/';
	}

	form.attr('action', action + codigoEstado);

	modal.find('.modal-body span').html('Tem certeza que deseja excluir o estado <strong>' + state + '</strong>?');
});


$('#confirmacaoExclusaoMunicipio').on('show.bs.modal', function(event) {

	var button = $(event.relatedTarget);

	var codigoMunicipio = button.data('codigo');
	var municipio = button.data('nome');
	

	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');

	if (!action.endsWith('/')) {
		action += '/';
	}

	form.attr('action', action + codigoMunicipio);

	modal.find('.modal-body span').html('Tem certeza que deseja excluir o município <strong>' + municipio + '</strong>?');
});

$('#confirmacaoExclusaoBairro').on('show.bs.modal', function(event) {

	var button = $(event.relatedTarget);

	var codigoBairro = button.data('codigo');
	var bairro = button.data('bairro');
	

	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');

	if (!action.endsWith('/')) {
		action += '/';
	}

	form.attr('action', action + codigoBairro);

	modal.find('.modal-body span').html('Tem certeza que deseja excluir o bairro <strong>' + bairro + '</strong>?');
});

$('#confirmacaoExclusaoImovel').on('show.bs.modal', function(event) {

	var button = $(event.relatedTarget);

	var codigoImovel = button.data('codigo');
	

	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');

	if (!action.endsWith('/')) {
		action += '/';
	}

	form.attr('action', action + codigoImovel);

	modal.find('.modal-body span').html('Tem certeza que deseja excluir o imóvel <strong>' + codigoImovel + '</strong>?');
});




