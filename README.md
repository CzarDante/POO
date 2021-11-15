# NavigatorAPI
Uma API gamificada com sobre exploração e sobrevivencia em que seu objetivo como player e desbravar o mar atras de ilhas para conseguir recursos e conseguir assim construir barcos mais fortes. Com um sistema de mercado global em que os player poderam comerciar os itens com outros players (Mercado à ser implementado na versão P2).

# Rotas

###### GET
/list-boats - Lista os barcos do jogo e o custo da criação de cada um.</br>
/market/list-all - Lista itens que estão a venda no market.</br>

###### POST
/create - Rota para criação da conta do player, recebe de parametros no body um JSON com o name do usuário.<br/>
/profile - Rota para ver o perfil do jogar, deve receber um header com o ID recebido ao criar o jogador.</br>
/create-boat - Rota para a criação do barco, deve ser passado por header o ID do player e por query o tipo do barco a ser criado.</br>
/gather-resource - Rota para recolher recursos na ilha em que o player esta.</br>
/market/list-specific - Rota para listar itens específicos.</br>
/market/sell-tem - Rota para inserir um item no market.</br>
/market/buy-item - Rota para comprar um item do market.</br>

###### DELETE
/delete - Rota para deletar a conta do jogador, deve ser passado um header com o ID da conta que deseja deletar.
