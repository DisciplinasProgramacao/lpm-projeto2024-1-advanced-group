����   A ;  5com/advanced/comidinhasveganas/services/PedidoService  java/lang/Object pedidoRepository >Lcom/advanced/comidinhasveganas/repositories/PedidoRepository; RuntimeVisibleAnnotations 8Lorg/springframework/beans/factory/annotation/Autowired; <init> ()V Code
   	 
 LineNumberTable LocalVariableTable this 7Lcom/advanced/comidinhasveganas/services/PedidoService; findAll ()Ljava/util/List; 	Signature D()Ljava/util/List<Lcom/advanced/comidinhasveganas/entities/Pedido;>;	        <com/advanced/comidinhasveganas/repositories/PedidoRepository   findById &(Ljava/lang/Long;)Ljava/util/Optional; X(Ljava/lang/Long;)Ljava/util/Optional<Lcom/advanced/comidinhasveganas/entities/Pedido;>;     ! ((Ljava/lang/Object;)Ljava/util/Optional; id Ljava/lang/Long; MethodParameters insert b(Lcom/advanced/comidinhasveganas/entities/Pedido;)Lcom/advanced/comidinhasveganas/entities/Pedido; :Lorg/springframework/transaction/annotation/Transactional;  ) * + save &(Ljava/lang/Object;)Ljava/lang/Object; - .com/advanced/comidinhasveganas/entities/Pedido pedido 0Lcom/advanced/comidinhasveganas/entities/Pedido; 	deleteAll  2 0 
 
deleteById (Ljava/lang/Long;)V  6 3 7 (Ljava/lang/Object;)V 
SourceFile PedidoService.java (Lorg/springframework/stereotype/Service; !                    	 
     /     *� �                                    4     
*� �  �                   
                 ?     *� +�  �                           " #  $    "    % &       '      B     *� +� ( � ,�                           . /  $    .    0 
       '      8     
*� � 1 �       
    " 	 #        
      3 4       '      C     *� +� 5 �       
    ' 
 (                " #  $    "    8    9      :  