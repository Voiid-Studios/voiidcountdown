# Message error
execute if score LanguageTimer Timer matches 0 run tellraw @s ["",{"text":"[","color":"dark_purple"},{"text":"VCT","color":"light_purple","bold":true},{"text":"]","color":"dark_purple"}," ",{"text":"Currently the language of Voiid Countdown Timer is English.","color":"red"}]


# Sounds
execute if score LanguageTimer Timer matches 0 run playsound minecraft:block.note_block.bass ambient @s ~ ~ ~ 0.7 1
execute if score LanguageTimer Timer matches 1 run playsound minecraft:entity.player.levelup ambient @s ~ ~ ~ 0.2 2


# System
execute if score LanguageTimer Timer matches 1 run clear @s minecraft:written_book[lore=[{"text":"vctc","italic":false}]]
execute if score LanguageTimer Timer matches 1 run function vct:internal/api/reset
execute if score LanguageTimer Timer matches 1 if score McVersionToNBTNew Timer matches 3.. run tellraw @s ["-------------------------------------\n",{"text":"Voiid Countdown Timer","bold":true,"color":"light_purple"}," ",{"text":"change the language, use or click the command\nto get the book:","color":"green"},"\n",{"text":"/function vct:book","bold":true,"color":"aqua","click_event":{"action":"run_command","command":"/function vct:book"},"hover_event":{"action":"show_text","value":"Click here to execute this command!"}},"\n-------------------------------------"]
execute if score LanguageTimer Timer matches 1 if score McVersionToNBTNew Timer matches ..2 run tellraw @s ["-------------------------------------\n",{"text":"Voiid Countdown Timer","color":"light_purple","bold":true}," ",{"text":"has been reloaded to\nchange the language, use or click the command\nto get the book:","color":"green"},"\n",{"text":"/function vct:book","bold":true,"clickEvent":{"action":"run_command","value":"/function vct:book"},"hoverEvent":{"action":"show_text","contents":"Click here to execute this command!"},"color":"aqua"},"\n-------------------------------------"]
execute if score LanguageTimer Timer matches 1 run scoreboard players set LanguageTimer Timer 0