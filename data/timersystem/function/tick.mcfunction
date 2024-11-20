execute if score Second2 Timer matches 0 if score Second1 Timer matches 0 if score Minute2 Timer matches 0 if score Minute1 Timer matches 0 if score Hour Timer matches 0 run schedule clear timersystem:addtick
execute store result bossbar voiidtimer:bar max run scoreboard players get MaxTimerSecs Timer
execute if score Tick Timer matches 20.. run scoreboard players remove TimerSecs Timer 1
bossbar set voiidtimer:bar players @a

execute if score Style Timer matches 0 run bossbar set voiidtimer:bar name ["",{"score":{"name":"Hour","objective":"Timer"},"color":"white"},{"text":":","color":"white"},{"score":{"name":"Minute1","objective":"Timer"},"color":"white"},{"score":{"name":"Minute2","objective":"Timer"},"color":"white"},{"text":":","color":"white"},{"score":{"name":"Second1","objective":"Timer"},"color":"white"},{"score":{"name":"Second2","objective":"Timer"},"color":"white"}]
execute if score Style Timer matches 0 if score PausedTimer Timer matches 1 if score LanguageTimer Timer matches 0 run bossbar set voiidtimer:bar name ["",{"score":{"name":"Hour","objective":"Timer"},"color":"white"},{"text":":","color":"white"},{"score":{"name":"Minute1","objective":"Timer"},"color":"white"},{"score":{"name":"Minute2","objective":"Timer"},"color":"white"},{"text":":","color":"white"},{"score":{"name":"Second1","objective":"Timer"},"color":"white"},{"score":{"name":"Second2","objective":"Timer"},"color":"white"},{"text":" (paused)","color":"dark_gray","italic":true}]
execute if score Style Timer matches 0 if score PausedTimer Timer matches 1 if score LanguageTimer Timer matches 1 run bossbar set voiidtimer:bar name ["",{"score":{"name":"Hour","objective":"Timer"},"color":"white"},{"text":":","color":"white"},{"score":{"name":"Minute1","objective":"Timer"},"color":"white"},{"score":{"name":"Minute2","objective":"Timer"},"color":"white"},{"text":":","color":"white"},{"score":{"name":"Second1","objective":"Timer"},"color":"white"},{"score":{"name":"Second2","objective":"Timer"},"color":"white"},{"text":" (pausado)","color":"dark_gray","italic":true}]
execute if score Style Timer matches 0 run bossbar set voiidtimer:bar color white

execute if score Style Timer matches 1 run bossbar set voiidtimer:bar name ["",{"score":{"name":"Hour","objective":"Timer"},"color":"blue"},{"text":":","color":"blue"},{"score":{"name":"Minute1","objective":"Timer"},"color":"blue"},{"score":{"name":"Minute2","objective":"Timer"},"color":"blue"},{"text":":","color":"blue"},{"score":{"name":"Second1","objective":"Timer"},"color":"blue"},{"score":{"name":"Second2","objective":"Timer"},"color":"blue"}]
execute if score Style Timer matches 1 if score PausedTimer Timer matches 1 if score LanguageTimer Timer matches 0 run bossbar set voiidtimer:bar name ["",{"score":{"name":"Hour","objective":"Timer"},"color":"blue"},{"text":":","color":"blue"},{"score":{"name":"Minute1","objective":"Timer"},"color":"blue"},{"score":{"name":"Minute2","objective":"Timer"},"color":"blue"},{"text":":","color":"blue"},{"score":{"name":"Second1","objective":"Timer"},"color":"blue"},{"score":{"name":"Second2","objective":"Timer"},"color":"blue"},{"text":" (paused)","color":"dark_gray","italic":true}]
execute if score Style Timer matches 1 if score PausedTimer Timer matches 1 if score LanguageTimer Timer matches 1 run bossbar set voiidtimer:bar name ["",{"score":{"name":"Hour","objective":"Timer"},"color":"blue"},{"text":":","color":"blue"},{"score":{"name":"Minute1","objective":"Timer"},"color":"blue"},{"score":{"name":"Minute2","objective":"Timer"},"color":"blue"},{"text":":","color":"blue"},{"score":{"name":"Second1","objective":"Timer"},"color":"blue"},{"score":{"name":"Second2","objective":"Timer"},"color":"blue"},{"text":" (pausado)","color":"dark_gray","italic":true}]
execute if score Style Timer matches 1 run bossbar set voiidtimer:bar color blue

execute if score Style Timer matches 2 run bossbar set voiidtimer:bar name ["",{"score":{"name":"Hour","objective":"Timer"},"color":"green"},{"text":":","color":"green"},{"score":{"name":"Minute1","objective":"Timer"},"color":"green"},{"score":{"name":"Minute2","objective":"Timer"},"color":"green"},{"text":":","color":"green"},{"score":{"name":"Second1","objective":"Timer"},"color":"green"},{"score":{"name":"Second2","objective":"Timer"},"color":"green"}]
execute if score Style Timer matches 2 if score PausedTimer Timer matches 1 if score LanguageTimer Timer matches 0 run bossbar set voiidtimer:bar name ["",{"score":{"name":"Hour","objective":"Timer"},"color":"green"},{"text":":","color":"green"},{"score":{"name":"Minute1","objective":"Timer"},"color":"green"},{"score":{"name":"Minute2","objective":"Timer"},"color":"green"},{"text":":","color":"green"},{"score":{"name":"Second1","objective":"Timer"},"color":"green"},{"score":{"name":"Second2","objective":"Timer"},"color":"green"},{"text":" (paused)","color":"dark_gray","italic":true}]
execute if score Style Timer matches 2 if score PausedTimer Timer matches 1 if score LanguageTimer Timer matches 1 run bossbar set voiidtimer:bar name ["",{"score":{"name":"Hour","objective":"Timer"},"color":"green"},{"text":":","color":"green"},{"score":{"name":"Minute1","objective":"Timer"},"color":"green"},{"score":{"name":"Minute2","objective":"Timer"},"color":"green"},{"text":":","color":"green"},{"score":{"name":"Second1","objective":"Timer"},"color":"green"},{"score":{"name":"Second2","objective":"Timer"},"color":"green"},{"text":" (pausado)","color":"dark_gray","italic":true}]
execute if score Style Timer matches 2 run bossbar set voiidtimer:bar color green

execute if score Style Timer matches 3 run bossbar set voiidtimer:bar name ["",{"score":{"name":"Hour","objective":"Timer"},"color":"light_purple"},{"text":":","color":"light_purple"},{"score":{"name":"Minute1","objective":"Timer"},"color":"light_purple"},{"score":{"name":"Minute2","objective":"Timer"},"color":"light_purple"},{"text":":","color":"light_purple"},{"score":{"name":"Second1","objective":"Timer"},"color":"light_purple"},{"score":{"name":"Second2","objective":"Timer"},"color":"light_purple"}]
execute if score Style Timer matches 3 if score PausedTimer Timer matches 1 if score LanguageTimer Timer matches 0 run bossbar set voiidtimer:bar name ["",{"score":{"name":"Hour","objective":"Timer"},"color":"light_purple"},{"text":":","color":"light_purple"},{"score":{"name":"Minute1","objective":"Timer"},"color":"light_purple"},{"score":{"name":"Minute2","objective":"Timer"},"color":"light_purple"},{"text":":","color":"light_purple"},{"score":{"name":"Second1","objective":"Timer"},"color":"light_purple"},{"score":{"name":"Second2","objective":"Timer"},"color":"light_purple"},{"text":" (paused)","color":"dark_gray","italic":true}]
execute if score Style Timer matches 3 if score PausedTimer Timer matches 1 if score LanguageTimer Timer matches 1 run bossbar set voiidtimer:bar name ["",{"score":{"name":"Hour","objective":"Timer"},"color":"light_purple"},{"text":":","color":"light_purple"},{"score":{"name":"Minute1","objective":"Timer"},"color":"light_purple"},{"score":{"name":"Minute2","objective":"Timer"},"color":"light_purple"},{"text":":","color":"light_purple"},{"score":{"name":"Second1","objective":"Timer"},"color":"light_purple"},{"score":{"name":"Second2","objective":"Timer"},"color":"light_purple"},{"text":" (pausado)","color":"dark_gray","italic":true}]
execute if score Style Timer matches 3 run bossbar set voiidtimer:bar color pink

execute if score Style Timer matches 4 run bossbar set voiidtimer:bar name ["",{"score":{"name":"Hour","objective":"Timer"},"color":"dark_purple"},{"text":":","color":"dark_purple"},{"score":{"name":"Minute1","objective":"Timer"},"color":"dark_purple"},{"score":{"name":"Minute2","objective":"Timer"},"color":"dark_purple"},{"text":":","color":"dark_purple"},{"score":{"name":"Second1","objective":"Timer"},"color":"dark_purple"},{"score":{"name":"Second2","objective":"Timer"},"color":"dark_purple"}]
execute if score Style Timer matches 4 if score PausedTimer Timer matches 1 if score LanguageTimer Timer matches 0 run bossbar set voiidtimer:bar name ["",{"score":{"name":"Hour","objective":"Timer"},"color":"dark_purple"},{"text":":","color":"dark_purple"},{"score":{"name":"Minute1","objective":"Timer"},"color":"dark_purple"},{"score":{"name":"Minute2","objective":"Timer"},"color":"dark_purple"},{"text":":","color":"dark_purple"},{"score":{"name":"Second1","objective":"Timer"},"color":"dark_purple"},{"score":{"name":"Second2","objective":"Timer"},"color":"dark_purple"},{"text":" (paused)","color":"dark_gray","italic":true}]
execute if score Style Timer matches 4 if score PausedTimer Timer matches 1 if score LanguageTimer Timer matches 1 run bossbar set voiidtimer:bar name ["",{"score":{"name":"Hour","objective":"Timer"},"color":"dark_purple"},{"text":":","color":"dark_purple"},{"score":{"name":"Minute1","objective":"Timer"},"color":"dark_purple"},{"score":{"name":"Minute2","objective":"Timer"},"color":"dark_purple"},{"text":":","color":"dark_purple"},{"score":{"name":"Second1","objective":"Timer"},"color":"dark_purple"},{"score":{"name":"Second2","objective":"Timer"},"color":"dark_purple"},{"text":" (pausado)","color":"dark_gray","italic":true}]
execute if score Style Timer matches 4 run bossbar set voiidtimer:bar color purple

execute if score Style Timer matches 5 run bossbar set voiidtimer:bar name ["",{"score":{"name":"Hour","objective":"Timer"},"color":"red"},{"text":":","color":"red"},{"score":{"name":"Minute1","objective":"Timer"},"color":"red"},{"score":{"name":"Minute2","objective":"Timer"},"color":"red"},{"text":":","color":"red"},{"score":{"name":"Second1","objective":"Timer"},"color":"red"},{"score":{"name":"Second2","objective":"Timer"},"color":"red"}]
execute if score Style Timer matches 5 if score PausedTimer Timer matches 1 if score LanguageTimer Timer matches 0 run bossbar set voiidtimer:bar name ["",{"score":{"name":"Hour","objective":"Timer"},"color":"red"},{"text":":","color":"red"},{"score":{"name":"Minute1","objective":"Timer"},"color":"red"},{"score":{"name":"Minute2","objective":"Timer"},"color":"red"},{"text":":","color":"red"},{"score":{"name":"Second1","objective":"Timer"},"color":"red"},{"score":{"name":"Second2","objective":"Timer"},"color":"red"},{"text":" (paused)","color":"dark_gray","italic":true}]
execute if score Style Timer matches 5 if score PausedTimer Timer matches 1 if score LanguageTimer Timer matches 1 run bossbar set voiidtimer:bar name ["",{"score":{"name":"Hour","objective":"Timer"},"color":"red"},{"text":":","color":"red"},{"score":{"name":"Minute1","objective":"Timer"},"color":"red"},{"score":{"name":"Minute2","objective":"Timer"},"color":"red"},{"text":":","color":"red"},{"score":{"name":"Second1","objective":"Timer"},"color":"red"},{"score":{"name":"Second2","objective":"Timer"},"color":"red"},{"text":" (pausado)","color":"dark_gray","italic":true}]
execute if score Style Timer matches 5 run bossbar set voiidtimer:bar color red

execute if score Style Timer matches 6 run bossbar set voiidtimer:bar name ["",{"score":{"name":"Hour","objective":"Timer"},"color":"yellow"},{"text":":","color":"yellow"},{"score":{"name":"Minute1","objective":"Timer"},"color":"yellow"},{"score":{"name":"Minute2","objective":"Timer"},"color":"yellow"},{"text":":","color":"yellow"},{"score":{"name":"Second1","objective":"Timer"},"color":"yellow"},{"score":{"name":"Second2","objective":"Timer"},"color":"yellow"}]
execute if score Style Timer matches 6 if score PausedTimer Timer matches 1 if score LanguageTimer Timer matches 0 run bossbar set voiidtimer:bar name ["",{"score":{"name":"Hour","objective":"Timer"},"color":"yellow"},{"text":":","color":"yellow"},{"score":{"name":"Minute1","objective":"Timer"},"color":"yellow"},{"score":{"name":"Minute2","objective":"Timer"},"color":"yellow"},{"text":":","color":"yellow"},{"score":{"name":"Second1","objective":"Timer"},"color":"yellow"},{"score":{"name":"Second2","objective":"Timer"},"color":"yellow"},{"text":" (paused)","color":"dark_gray","italic":true}]
execute if score Style Timer matches 6 if score PausedTimer Timer matches 1 if score LanguageTimer Timer matches 1 run bossbar set voiidtimer:bar name ["",{"score":{"name":"Hour","objective":"Timer"},"color":"yellow"},{"text":":","color":"yellow"},{"score":{"name":"Minute1","objective":"Timer"},"color":"yellow"},{"score":{"name":"Minute2","objective":"Timer"},"color":"yellow"},{"text":":","color":"yellow"},{"score":{"name":"Second1","objective":"Timer"},"color":"yellow"},{"score":{"name":"Second2","objective":"Timer"},"color":"yellow"},{"text":" (pausado)","color":"dark_gray","italic":true}]
execute if score Style Timer matches 6 run bossbar set voiidtimer:bar color yellow

execute store result bossbar voiidtimer:bar value run scoreboard players get TimerSecs Timer
execute if score Tick Timer matches 20.. run function timersystem:remove1second
execute if score Second2 Timer matches -1 run function timersystem:remove10seconds
execute if score Second1 Timer matches -1 run function timersystem:remove1minute
execute if score Minute2 Timer matches -1 run function timersystem:remove10minutes
execute if score Minute1 Timer matches -1 run function timersystem:remove1hour
execute if score Second2 Timer matches 10.. run scoreboard players add Second1 Timer 1
execute if score Second2 Timer matches 10.. run scoreboard players set Second2 Timer 0
execute if score Second1 Timer matches 6.. run scoreboard players add Minute2 Timer 1
execute if score Second1 Timer matches 6.. run scoreboard players set Second1 Timer 0
execute if score Minute2 Timer matches 10.. run scoreboard players add Minute1 Timer 1
execute if score Minute2 Timer matches 10.. run scoreboard players set Minute2 Timer 0
execute if score ToggleHour Timer matches 1 if score Minute1 Timer matches 6.. run scoreboard players add Hour Timer 1
execute if score ToggleHour Timer matches 1 if score Minute1 Timer matches 6.. run scoreboard players remove Minute1 Timer 6
execute if score ToggleHour Timer matches 0 if score Hour Timer matches 1.. run scoreboard players add Minute1 Timer 6
execute if score ToggleHour Timer matches 0 if score Hour Timer matches 1.. run scoreboard players remove Hour Timer 1
execute if score ToggleHour Timer matches 0 run execute if score Minute1 Timer matches 0 if score Minute2 Timer matches 0 if score Second2 Timer matches 1 if score Second1 Timer matches 0 run schedule function timersystem:ended 2t replace
execute if score ToggleHour Timer matches 1 run execute if score Hour Timer matches 0 if score Minute1 Timer matches 0 if score Minute2 Timer matches 0 if score Second2 Timer matches 1 if score Second1 Timer matches 0 run schedule function timersystem:ended 2t replace