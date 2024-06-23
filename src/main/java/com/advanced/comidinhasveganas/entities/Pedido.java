Êþº¾   A ¶  .com/advanced/comidinhasveganas/entities/Pedido  java/lang/Object id Ljava/lang/Long; RuntimeVisibleAnnotations Ljakarta/persistence/Id; $Ljakarta/persistence/GeneratedValue; strategy $Ljakarta/persistence/GenerationType; IDENTITY itens Ljava/util/List; 	Signature FLjava/util/List<Lcom/advanced/comidinhasveganas/entities/ItemPedido;>; Ljakarta/persistence/OneToMany;  Ljakarta/persistence/JoinColumn; name 	pedido_id 
tipoPedido Ljava/lang/String; 
precoTotal D <init> ()V Code
      java/util/ArrayList
  	  "   LineNumberTable LocalVariableTable this 0Lcom/advanced/comidinhasveganas/entities/Pedido; (Ljava/lang/String;)V
  ) * ' setTipoPedido MethodParameters getId ()Ljava/lang/Long;	  /   getItens ()Ljava/util/List; H()Ljava/util/List<Lcom/advanced/comidinhasveganas/entities/ItemPedido;>; getTipoPedido ()Ljava/lang/String;	  6   getPrecoTotal ()D	  :   addItem 7(Lcom/advanced/comidinhasveganas/entities/ItemPedido;)V > @ ? java/util/List A B add (Ljava/lang/Object;)Z item 4Lcom/advanced/comidinhasveganas/entities/ItemPedido; addItens (Ljava/util/List;)V I(Ljava/util/List<Lcom/advanced/comidinhasveganas/entities/ItemPedido;>;)V > I J K addAll (Ljava/util/Collection;)Z LocalVariableTypeTable setPrecoTotal O normal
 Q S R java/lang/String T U equalsIgnoreCase (Ljava/lang/String;)Z > W X Y stream ()Ljava/util/stream/Stream;   [ \ ] applyAsDouble '()Ljava/util/function/ToDoubleFunction; _ a ` java/util/stream/Stream b c mapToDouble F(Ljava/util/function/ToDoubleFunction;)Ljava/util/stream/DoubleStream; e g f java/util/stream/DoubleStream h 8 sum j fechado@@       n "java/lang/IllegalArgumentException p Tipo de pedido desconhecido.
 m r  ' StackMapTable toString v java/lang/StringBuilder
 u  > y z { iterator ()Ljava/util/Iterator; }  ~ java/util/Iterator   next ()Ljava/lang/Object;  2com/advanced/comidinhasveganas/entities/ItemPedido
   t 4
 u    append -(Ljava/lang/String;)Ljava/lang/StringBuilder;  
 }    hasNext ()Z  PreÃ§o total: R$ 
   7 8
 u    (D)Ljava/lang/StringBuilder;
 u  sb Ljava/lang/StringBuilder; 
SourceFile Pedido.java Ljakarta/persistence/Entity; Ljakarta/persistence/Table; 
tb_pedidos BootstrapMethods
 ¡ £ ¢ "java/lang/invoke/LambdaMetafactory ¤ ¥ metafactory Ì(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;   (Ljava/lang/Object;)D §
  ª « ¬ getSubTotal ()Ljava/lang/Double; © 7(Lcom/advanced/comidinhasveganas/entities/ItemPedido;)D ® InnerClasses ² %java/lang/invoke/MethodHandles$Lookup ´ java/lang/invoke/MethodHandles Lookup !                  	  
e                      s                    B     *· *» Y·  µ !±    #            $        % &     '     U     *· *» Y·  µ !*+¶ (±    #       !    "  # $        % &         +        , -     /     *´ .°    #       & $        % &    0 1      2    /     *´ !°    #       * $        % &    3 4     /     *´ 5°    #       . $        % &    * '     >     *+µ 5±    #   
    2  3 $        % &         +        7 8     /     *´ 9¯    #       6 $        % &    ; <     D     *´ !+¹ = W±    #   
    :  ; $        % &      C D  +    C    E F      G    V     *´ !+¹ H W±    #   
    >  ? $        % &         L           +        M           L*´ 5N¶ P "**´ !¹ V º Z  ¹ ^ ¹ d µ 9§ #*´ 5i¶ P * kµ 9§ » mYo· q¿±    #       B  C ( D 7 E > F A G K I $       L % &   s    +	  t 4     µ     I» uY· wL*´ !¹ x N§ -¹ | À M+,¶ ¶ ¶ W-¹  ÿâ+¶ *¶ ¶ W+¶ °    #       M  N  O - N 6 Q D R $        I % &    A      C D  s    ÿ    u  }                   s       ¦  ¨ ­ ¯ °   
  ± ³ µ 