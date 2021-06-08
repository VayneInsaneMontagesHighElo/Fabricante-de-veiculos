# Netbeans-Veiculos-07-06-Updated

Implementar botoes de pesquisa
Colocar criptografia
FrmModelo - borda, borda na tabela, dispose, titulo 

tbfabricante
id	int(11) pk ai
marca	varchar(80)	
telefone	varchar(15)
site	varchar(200)
email	varchar(200)

tbmodelo
id	int(11) pk ai
modelo	varchar(80)
marca	varchar(50)
ano	int(11)

tbusuario
id	int(11) pk ai
nome	varchar(80)
email	varchar(200)
senha	varchar(100)
tipo	varchar(200)

tbveiculo
id	int(11) pk ai
marca	varchar(80)
modelo	varchar(80)
ano	int(11)
cor	varchar(25)
placa	varchar(8)
motor	varchar(10)
km	int(11)
valor	decimal(10,2)
