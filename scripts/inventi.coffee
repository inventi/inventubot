# Description:
#   Remind the contacts of UAB Inventi

module.exports = (robot) ->
  robot.hear /get contacts/i, (res) ->
    res.send "Verkių g. 25C, 2 a., LT-08223 Vilnius"
