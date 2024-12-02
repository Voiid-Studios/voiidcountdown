# This is all the code to get the book in the different languages
## This is also the most spagetti code in all of VCT

execute if score LanguageTimer Timer matches 0 run give @s written_book[written_book_content={pages:['[["",{"text":"Add Time","bold":true,"underlined":true},"\\n\\n",{"text":"Add 10 minutes","bold":true,"color":"dark_green","clickEvent":{"action":"run_command","value":"/function vct:internal/player/a10mins"}},"\\n",{"text":"Add 1 minute","bold":true,"color":"dark_green","clickEvent":{"action":"run_command","value":"/function vct:internal/player/a1min"}},"\\n",{"text":"Add 10 seconds","bold":true,"color":"dark_green","clickEvent":{"action":"run_command","value":"/function vct:internal/player/a10secs"}},"\\n",{"text":"Add 1 second","bold":true,"color":"dark_green","clickEvent":{"action":"run_command","value":"/function vct:internal/player/a1sec"}},"\\n\\n",{"text":"Remove Time","bold":true,"underlined":true},"\\n\\n",{"text":"Remove 10 minutes","bold":true,"color":"dark_red","clickEvent":{"action":"run_command","value":"/function vct:internal/player/r10mins"}},"\\n",{"text":"Remove 1 minute","bold":true,"color":"dark_red","clickEvent":{"action":"run_command","value":"/function vct:internal/player/r1min"}},"\\n",{"text":"Remove 10 seconds","bold":true,"color":"dark_red","clickEvent":{"action":"run_command","value":"/function vct:internal/player/r10secs"}},"\\n",{"text":"Remove 1 second","bold":true,"color":"dark_red","clickEvent":{"action":"run_command","value":"/function vct:internal/player/r1sec"}}]]','[["",{"text":"Controls","bold":true,"underlined":true},"\\n\\n",{"text":"Start","bold":true,"color":"dark_green","clickEvent":{"action":"run_command","value":"/function vct:internal/controls/start"},"hoverEvent":{"action":"show_text","contents":"Starts Countdown"}},"\\n",{"text":"Pause","bold":true,"color":"gold","clickEvent":{"action":"run_command","value":"/function vct:internal/controls/pause"},"hoverEvent":{"action":"show_text","contents":"Pause Countdown"}},"\\n",{"text":"Reset","bold":true,"color":"dark_red","clickEvent":{"action":"run_command","value":"/function vct:internal/controls/reset"},"hoverEvent":{"action":"show_text","contents":"Stop and Reset Countdown"}},"\\n\\n",{"text":"Colors","underlined":true,"bold":true},"\\n\\n",{"text":"█","bold":true,"color":"white","clickEvent":{"action":"run_command","value":"/scoreboard players set Style Timer 0"},"hoverEvent":{"action":"show_text","contents":"Change color to Default (White)"}},{"text":" ","bold":true},{"text":"█","bold":true,"color":"blue","clickEvent":{"action":"run_command","value":"/scoreboard players set Style Timer 1"},"hoverEvent":{"action":"show_text","contents":"Change color to Blue"}},{"text":" ","bold":true},{"text":"█","bold":true,"color":"green","clickEvent":{"action":"run_command","value":"/scoreboard players set Style Timer 2"},"hoverEvent":{"action":"show_text","contents":"Change color to Green"}},{"text":" ","bold":true},{"text":"█","bold":true,"color":"light_purple","clickEvent":{"action":"run_command","value":"/scoreboard players set Style Timer 3"},"hoverEvent":{"action":"show_text","contents":"Change color to Pink"}},{"text":" ","bold":true},{"text":"█","bold":true,"color":"dark_purple","clickEvent":{"action":"run_command","value":"/scoreboard players set Style Timer 4"},"hoverEvent":{"action":"show_text","contents":"Change color to Purple"}},{"text":" ","bold":true},{"text":"█","bold":true,"color":"red","clickEvent":{"action":"run_command","value":"/scoreboard players set Style Timer 5"},"hoverEvent":{"action":"show_text","contents":"Change color to Red"}},{"text":" ","bold":true},{"text":"█","bold":true,"color":"yellow","clickEvent":{"action":"run_command","value":"/scoreboard players set Style Timer 6"},"hoverEvent":{"action":"show_text","contents":"Change color to Yellow"}},"\\n\\nClick on one of the colors to change the color of the Timer."]]','[["",{"text":"Language","bold":true,"underlined":true},"\\n\\n",{"text":"Spanish","bold":true,"color":"gold","clickEvent":{"action":"run_command","value":"/function vct:internal/api/langes"}},"\\n",{"text":"English","bold":true,"color":"blue","clickEvent":{"action":"run_command","value":"/function vct:internal/api/langen"}},"\\n\\n",{"text":"DANGER ZONE!","color":"dark_red","bold":true,"underlined":true},"\\n\\n",{"text":"Uninstall","clickEvent":{"action":"run_command","value":"/function vct:internal/init/uninstall"},"hoverEvent":{"action":"show_text","contents":"Are you sure you want to do it? I will miss you :c"},"color":"dark_red","bold":true},"\\n\\nPressing uninstall will delete the ENTIRE datapack. I will miss you if you leave :c"]]','[["",{"text":"Thank You!","bold":true,"underlined":true},"\\n\\nThank you very much for using this datapack, I appreciate it very much ",{"text":"❤","color":"red"},"\\n~ MaxxVoiid"]]'],title:"Voiid Countdown Timer - Config",author:MaxxVoiid},lore=['["",{"text":"vctc","italic":false}]']]
execute if score LanguageTimer Timer matches 1 run give @s written_book[written_book_content={pages:['[["",{"text":"Añadir tiempo","bold":true,"underlined":true},"\\n\\n",{"text":"Añadir 10 min.","bold":true,"color":"dark_green","clickEvent":{"action":"run_command","value":"/function vct:internal/player/a10mins"}},"\\n",{"text":"Añadir 1 min.","bold":true,"color":"dark_green","clickEvent":{"action":"run_command","value":"/function vct:internal/player/a1min"}},"\\n",{"text":"Añadir 10 seg.","bold":true,"color":"dark_green","clickEvent":{"action":"run_command","value":"/function vct:internal/player/a10secs"}},"\\n",{"text":"Añadir 1 seg.","bold":true,"color":"dark_green","clickEvent":{"action":"run_command","value":"/function vct:internal/player/a1sec"}},"\\n\\n",{"text":"Remover tiempo","underlined":true,"bold":true},"\\n\\n",{"text":"Remover 10 min.","bold":true,"color":"dark_red","clickEvent":{"action":"run_command","value":"/function vct:internal/player/r10mins"}},"\\n",{"text":"Remover 1 min.","bold":true,"color":"dark_red","clickEvent":{"action":"run_command","value":"/function vct:internal/player/r1min"}},"\\n",{"text":"Remover 10 seg.","bold":true,"color":"dark_red","clickEvent":{"action":"run_command","value":"/function vct:internal/player/r10secs"}},"\\n",{"text":"Remover 1 seg.","bold":true,"color":"dark_red","clickEvent":{"action":"run_command","value":"/function vct:internal/player/r1sec"}}]]','[["",{"text":"Controles","bold":true,"underlined":true},"\\n\\n",{"text":"Iniciar","bold":true,"color":"dark_green","clickEvent":{"action":"run_command","value":"/function vct:internal/controls/start"},"hoverEvent":{"action":"show_text","contents":"Empieza la cuenta atras"}},"\\n",{"text":"Pausar","bold":true,"color":"gold","clickEvent":{"action":"run_command","value":"/function vct:internal/controls/pause"},"hoverEvent":{"action":"show_text","contents":"Pausa la cuenta atras"}},"\\n",{"text":"Reiniciar","bold":true,"color":"dark_red","clickEvent":{"action":"run_command","value":"/function vct:internal/controls/reset"},"hoverEvent":{"action":"show_text","contents":"Para y reinicia la cuenta atras"}},"\\n\\n",{"text":"Colores","underlined":true,"bold":true},"\\n\\n",{"text":"█","bold":true,"color":"white","clickEvent":{"action":"run_command","value":"/scoreboard players set Style Timer 0"},"hoverEvent":{"action":"show_text","contents":"Cambiar el color a Por Defecto (Blanco)"}},{"text":" ","bold":true},{"text":"█","bold":true,"color":"blue","clickEvent":{"action":"run_command","value":"/scoreboard players set Style Timer 1"},"hoverEvent":{"action":"show_text","contents":"Cambiar el color a Azul"}},{"text":" ","bold":true},{"text":"█","bold":true,"color":"green","clickEvent":{"action":"run_command","value":"/scoreboard players set Style Timer 2"},"hoverEvent":{"action":"show_text","contents":"Cambiar el color a Verde"}},{"text":" ","bold":true},{"text":"█","bold":true,"color":"light_purple","clickEvent":{"action":"run_command","value":"/scoreboard players set Style Timer 3"},"hoverEvent":{"action":"show_text","contents":"Cambiar el color a Rosado"}},{"text":" ","bold":true},{"text":"█","bold":true,"color":"dark_purple","clickEvent":{"action":"run_command","value":"/scoreboard players set Style Timer 4"},"hoverEvent":{"action":"show_text","contents":"Cambiar el color a Purpura"}},{"text":" ","bold":true},{"text":"█","bold":true,"color":"red","clickEvent":{"action":"run_command","value":"/scoreboard players set Style Timer 5"},"hoverEvent":{"action":"show_text","contents":"Cambiar el color a Rojo"}},{"text":" ","bold":true},{"text":"█","bold":true,"color":"yellow","clickEvent":{"action":"run_command","value":"/scoreboard players set Style Timer 6"},"hoverEvent":{"action":"show_text","contents":"Cambiar el color a Amarillo"}},"\\n\\nHaz clic en uno de los colores para cambiar el color del Timer."]]','[["",{"text":"Idioma","underlined":true,"bold":true},"\\n\\n",{"text":"Español","bold":true,"color":"gold","clickEvent":{"action":"run_command","value":"/function vct:internal/api/langes"}},"\\n",{"text":"Ingles","bold":true,"color":"blue","clickEvent":{"action":"run_command","value":"/function vct:internal/api/langen"}},"\\n\\n",{"text":"ZONA DE PELIGRO!","color":"dark_red","bold":true,"underlined":true},"\\n\\n",{"text":"Desinstalar","clickEvent":{"action":"run_command","value":"/function vct:internal/init/uninstall"},"hoverEvent":{"action":"show_text","contents":"Estas seguro de hacerlo? Te echare de menos :c"},"color":"dark_red","bold":true},"\\n\\nAl presionar desinstalar, se borrara TODO el datapack. Te echare de menos si te vas :c\\n",{"text":"​​​","underlined":true}]]','[["",{"text":"Gracias!","underlined":true,"bold":true},"\\n\\nGracias por usar mi datapack, lo aprecio mucho ",{"text":"❤","color":"red"},"\\n~ MaxxVoiid"]]'],title:"Voiid Countdown Timer - Config",author:MaxxVoiid},lore=['["",{"text":"vctc","italic":false}]']]