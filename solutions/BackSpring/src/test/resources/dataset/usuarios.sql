INSERT INTO usuario (
		id, 
		created, 
		email, 
		tipousuario, 
		disponivel, 
		nome, 
		cpf, 
		nascimento, 
		celular, 
		senha, 
		account_activate_token, 
		account_activate_token_expiration, 
		password_reset_token, 
		password_reset_token_expiration)

	VALUES (
		1001, 
		now(), 
		'jlenon7@hotmail.com', 
		1, 
		false, 
		'João Lenon', 
		'092.862.989-94', 
		'12/09/2000', 
		'(45) 99955-3220', 
		'$2a$10$bAdAVLvM.k3DqPaPYi0gnO1OffPSHLref8MElAk.u.fFQ17v9YKC2', 
		'f786c907-032e-451b-ac93-8508dec75a3d', 
		'2019-12-02', 
		'f786c907-032e-451b-ac93-8508dec75a3d', 
		'2019-12-02'), (
		1002, 
		now(), 
		'adryell.silva10@gmail.com', 
		1, 
		false, 
		'Adryell Nathann', 
		'116.506.789-75', 
<<<<<<< HEAD
		'10/27/2000', 
=======
		'27/10/2000', 
>>>>>>> 585da9e321dcfabbc1de44100e4ff638bf354f8a
		'(45) 99996-4523', 
		'$2a$10$bAdAVLvM.k3DqPaPYi0gnO1OffPSHLref8MElAk.u.fFQ17v9YKC2', 
		'f786c907-032e-451b-ac93-8508dec75a3d', 
		'2019-12-02', 
		'f786c907-032e-451b-ac93-8508dec75a3d', 
		'2019-12-02');
