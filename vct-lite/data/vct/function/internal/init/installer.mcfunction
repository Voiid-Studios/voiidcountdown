# This is the installer, where it checks if you have a compatible version

## Get your version of MC
execute if score InstalledOneTime Timer matches 0 run tellraw @a [{"text":"■","bold":false,"color":"light_purple"},{"text":" VCT: ","bold":true,"color":"light_purple"},{"text":"Checking compatibility","bold":false,"color":"light_purple"}]

execute store result score McVersion Timer run data get entity @r DataVersion
function vct:internal/api/getversionname

## Check if it is an incompatible version
### Verify only if you have 1.21.5, a version lower than 1.15 or a version upper than 1.21.11.
execute if score McVersion Timer matches 4298.. run scoreboard players set McVersionToNBTNew Timer 3
execute if score McVersion Timer matches 3940..4297 run scoreboard players set McVersionToNBTNew Timer 2

execute if score InstalledOneTime Timer matches 0 if score McVersionToNBTNew Timer matches 3 run tellraw @a [{"text":"■","bold":false,"color":"aqua"},{"text":" VCT: ","bold":true,"color":"aqua"},{"text":"Using new NBT tags (1.21.5+)","bold":false,"color":"aqua"}]

execute if score McVersion Timer matches ..1975 run scoreboard players add McVersionIncompatible Timer 1
execute if score McVersion Timer matches 4764.. run scoreboard players add McVersionIncompatible Timer 1

execute if score McVersionIncompatible Timer matches 0 if score InstalledOneTime Timer matches 0 run tellraw @a [{"text":"■","bold":false,"color":"green"},{"text":" VCT: ","bold":true,"color":"green"},{"text":"Compatibility PASS (","bold":false,"color":"green"},{"score":{"name":"McVersion","objective":"Timer"},"bold":false,"color":"green"},{"text":" / ","bold":false,"color":"green"},{"storage":"vct:data","nbt":"VersionName","bold":false,"color":"green"},{"text":")","bold":false,"color":"green"}]
execute if score McVersionIncompatible Timer matches 1 if score InstalledOneTime Timer matches 0 run tellraw @a [{"text":"■","bold":false,"color":"dark_red"},{"text":" VCT: ","bold":true,"color":"dark_red"},{"text":"Compatibility FAIL (","bold":false,"color":"dark_red"},{"score":{"name":"McVersion","objective":"Timer"},"bold":false,"color":"dark_red"},{"text":" / ","bold":false,"color":"dark_red"},{"storage":"vct:data","nbt":"VersionName","bold":false,"color":"dark_red"},{"text":")","bold":false,"color":"dark_red"}]

execute if score McVersionIncompatible Timer matches 1 run tellraw @a ["-------------------------------------\n",{"text":"Voiid Countdown Timer","bold":true,"color":"light_purple"}," ",{"text":"can not be installed\non this version of Minecraft! ","color":"red"},{"text":"(","color":"gold"},{"storage":"vct:data","nbt":"VersionName","color":"gold"},{"text":")","color":"gold"},"\n\nThe version you are using is ",{"text":"incompatible ","color":"red"},"\nwith Void Countdown Timer.\n",{"text":"VCT LITE works in Minecraft 1.15 - 1.21.11.","color":"aqua"},"\n\nPlease completely remove the datapack\nfrom this world. We have already disabled\nit for you ;)\n\n",{"text":"Sorry about that!","color":"yellow"},"\n-------------------------------------"]
execute if score McVersionIncompatible Timer matches 1 run schedule function vct:internal/api/uninstall 3s

## If your version is compatible, you will be notified with a welcome message
execute if score McVersionIncompatible Timer matches 0 if score InstalledOneTime Timer matches 0 run execute as @a at @s run playsound minecraft:block.note_block.bell master @s ~ ~ ~ 0.7 2
execute if score McVersionIncompatible Timer matches 0 if score McVersion Timer matches 4298.. if score InstalledOneTime Timer matches 0 run tellraw @a ["-------------------------------------\n",{"text":"Voiid Countdown Timer","bold":true,"color":"light_purple"}," ",{"text":"has been installed\ncorrectly! ","color":"green"},{"text":"(Running LITE on ","color":"aqua"},{"storage":"vct:data","nbt":"VersionName","color":"aqua"},{"text":")","color":"aqua"},"\n\nGet the ",{"text":"config book","color":"yellow"}," using this command:\n",{"text":"/function vct:book","bold":true,"color":"aqua","click_event":{"action":"run_command","command":"/function vct:book"},"hover_event":{"action":"show_text","value":"Click here to execute this command!"}},"\n\nYou can click on the command to\nexecute it quickly.\n\n",{"text":"Thank you for using VCT Lite!","color":"green"},"\n-------------------------------------"]
execute if score McVersionIncompatible Timer matches 0 if score McVersion Timer matches ..4297 if score InstalledOneTime Timer matches 0 run tellraw @a ["-------------------------------------\n",{"text":"Voiid Countdown Timer","bold":true,"color":"light_purple"}," ",{"text":"has been installed\ncorrectly! ","color":"green"},{"text":"(Running LITE on ","color":"aqua"},{"storage":"vct:data","nbt":"VersionName","color":"aqua"},{"text":")","color":"aqua"},"\n\nGet the ",{"text":"config book","color":"yellow"}," using this command:\n",{"text":"/function vct:book","bold":true,"color":"aqua","clickEvent":{"action":"run_command","value":"/function vct:book"},"hoverEvent":{"action":"show_text","contents":"Click here to execute this command!"}},"\n\nYou can click on the command to\nexecute it quickly.\n\n",{"text":"Thank you for using VCT Lite!","color":"green"},"\n-------------------------------------"]
execute if score McVersionIncompatible Timer matches 0 if score InstalledOneTime Timer matches 0 run tellraw @a [{"text":"■","bold":false,"color":"green"},{"text":" VCT: ","bold":true,"color":"green"},{"text":"Ready!","bold":false,"color":"green"}]
execute if score McVersionIncompatible Timer matches 0 if score InstalledOneTime Timer matches 0 run scoreboard players add InstalledOneTime Timer 1