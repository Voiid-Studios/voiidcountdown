scoreboard players add Minute1 Timer 1
scoreboard players add TimerSecs Timer 600
scoreboard players add MaxTimerSecs Timer 600

# Message changed time
playsound minecraft:entity.player.levelup ambient @s ~ ~ ~ 0.2 2
## EN
execute if score LanguageTimer Timer matches 0 run tellraw @s ["",{"text":"[","color":"dark_purple"},{"text":"VCT","color":"light_purple","bold":true},{"text":"]","color":"dark_purple"}," ",{"text":"10 minutes have been added to the timer! It is now in","color":"green"}," ",{"score":{"name":"Hour","objective":"Timer"},"color":"dark_green"},{"text":":","color":"dark_green"},{"score":{"name":"Minute1","objective":"Timer"},"color":"dark_green"},{"score":{"name":"Minute2","objective":"Timer"},"color":"dark_green"},{"text":":","color":"dark_green"},{"score":{"name":"Second1","objective":"Timer"},"color":"dark_green"},{"score":{"name":"Second2","objective":"Timer"},"color":"dark_green"}]
## ES 
execute if score LanguageTimer Timer matches 1 run tellraw @s ["",{"text":"[","color":"dark_purple"},{"text":"VCT","color":"light_purple","bold":true},{"text":"]","color":"dark_purple"}," ",{"text":"Se han agregado 10 minutos al temporizador! Ahora está en","color":"green"}," ",{"score":{"name":"Hour","objective":"Timer"},"color":"dark_green"},{"text":":","color":"dark_green"},{"score":{"name":"Minute1","objective":"Timer"},"color":"dark_green"},{"score":{"name":"Minute2","objective":"Timer"},"color":"dark_green"},{"text":":","color":"dark_green"},{"score":{"name":"Second1","objective":"Timer"},"color":"dark_green"},{"score":{"name":"Second2","objective":"Timer"},"color":"dark_green"}]