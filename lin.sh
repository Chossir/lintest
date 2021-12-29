#!/bin/bash
function cpu() {
   cpuse=$(vmstat | awk '{if(NR==3)print $13+$14}')
   io=$(vmstat | awk '{if(NR==3)print $16}')
   echo"CPU :${cpuse}%  ,  IO:${io}%"
}
function mmo() {
   total=$(free -m | awk 'if(NR==2)printf "1.%f",$2/1024)}')
   used=$(free -m | awk 'if(NR==2)printf "1.%f",($2-$NF)/1024)}')
   able=$(free -m | awk 'if(NR==2)printf "1.%f",$NF/1024}')
   echo"RAM: ${total}G  ,  Use:${used}G  ,  Surplus:${able}G"
}
disk(){
   fs=$(df -h | awk '/^\/dev/{print $1}')
   for p in $fs ; do
      mounted=$(df -h | awk -v p=$p  '$1==p(print,$NF}')
      size=$(df -h | awk -v p=$p  '$1==p(print,$2}')
      used=$(df -h | awk -v p=$p  '$1==p(print,$3}')
      used_percent=$(df -h | awk -v p=$p  '$1==p(print,$5}')
      echo"Disk: $mounted  ,  Size:$size  ,  Use:$used  ,  Utili:$used_precent"
   done
}
tcp_status(){
   summary=$(etstat -antp | awk '{a[$6]++}END{for(i in a) printf i":"a[i]" "}')
   echo"TCP connection:$summary"
}
cpu
mmo
disk
tcp_status                           