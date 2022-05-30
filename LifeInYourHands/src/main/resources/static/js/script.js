
function limpa_formulario_cep() {
    //Limpa valores do formulário de cep.
    document.getElementById('logradouro').value=("");
    document.getElementById('bairro').value=("");
    document.getElementById('cidade').value=("");
    document.getElementById('uf').value=("");
}

function meu_callback(conteudo) {
if (!("erro" in conteudo)) {
    //Atualiza os campos com os valores.
    document.getElementById('logradouro').value=(conteudo.logradouro);
    document.getElementById('bairro').value=(conteudo.bairro);
    document.getElementById('cidade').value=(conteudo.localidade);
    document.getElementById('uf').value=(conteudo.uf);
} //end if.
else {
    //CEP não Encontrado.
    limpa_formulario_cep();
    alert("CEP não encontrado.");
}
}

function pesquisacep(valor) {

//Nova variável "cep" somente com dígitos.
var cep = valor.replace(/\D/g, '');

//Verifica se campo cep possui valor informado.
if (cep != "") {

    //Expressão regular para validar o CEP.
    var validacep = /^[0-9]{8}$/;

    //Valida o formato do CEP.
    if(validacep.test(cep)) {

        //Preenche os campos com "..." enquanto consulta webservice.
        document.getElementById('logradouro').value="...";
        document.getElementById('bairro').value="...";
        document.getElementById('cidade').value="...";
        document.getElementById('uf').value="...";

        //Cria um elemento javascript.
        var script = document.createElement('script');

        //Sincroniza com o callback.
        script.src = 'https://viacep.com.br/ws/'+ cep + '/json/?callback=meu_callback';

        //Insere script no documento e carrega o conteúdo.
        document.body.appendChild(script);

    } //end if.
    else {
        //cep é inválido.
        limpa_formulario_cep();
        alert("Formato de CEP inválido.");
    }
} //end if.
else {
    //cep sem valor, limpa formulário.
    limpa_formulario_cep();
}
};


function preencherMedicosByEspecialidade(url){
	//alert("Chegou no inicio");

	
		var idEspecialidade = $("#especialidade_id").val();
	    //alert("Especialidade id " + idEspecialidade);
	    
		if(idEspecialidade != NaN && idEspecialidade > 0) {
		
			//alert("Entrou IF "+ url);
			$.ajax({
				method: "POST",
				url: url,
				data: JSON.stringify({idEspecialidade : idEspecialidade}),
				contentType: "application/json; charset=utf-8",
				success: function (response){			  		  
					
					var option = '<option value='+ '"' + 0 + '"' + '> Selecione o médico</option>';
					$.each(response, function(i, obj) {
						option += '<option value='+ '"' + obj.id + '"' + '>' + obj.nome + '</option>';
					})
					$("#medico_id").html(option).show();  
					
										
				}		
			}).fail(function (xhr, status, errorThrown) {
				alert("Falha " + xhr.responseText);
			});
    	 } 
    
    
}
    
    
    
function preencherGradeByMedico(url){

		var idMedico = $("#medico_id").val();
		
		if(idMedico != NaN && idMedico > 0) {
			
			$.ajax({
				method: "POST",
				url: url,
				data: JSON.stringify({idMedico : idMedico}),
				contentType: "application/json; charset=utf-8",
				success: function (response){			  		  
					var option = '<option value='+ '"' + 0 + '"' + '> Selecione o Horário Desejado</option>';
					$.each(response, function(i, obj) {
					
						var dataOriginal = new Date(obj.dataDeAtendimento);
										
						var dataFormatada = 
						new Intl.DateTimeFormat('pt-BR', 
						{ 	
							day: 'numeric', 
							month: 'numeric', 
							year: 'numeric',	
							hour: 'numeric', 
							minute: 'numeric'							
						} ).format(dataOriginal);		
						
						option += '<option value='+ '"' + dataFormatada + '"' + '>' + dataFormatada + '</option>';
					})
					$("#grade_horario").html(option).show();  
					
					
				}		
			}).fail(function (xhr, status, errorThrown) {
				alert("Falha " + xhr.responseText);
			});
    	} 
    
    
}