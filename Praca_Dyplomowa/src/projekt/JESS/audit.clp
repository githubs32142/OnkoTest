(clear)
(reset)

(deftemplate Punkty
(slot pkt )
)

(defrule regula1
 (or( Punkty (  pkt 2))( Punkty (  pkt 1)))
=>
 (printout t "czu³oœæ 53-92%, swowodnoœæ 81-95%" crlf)
)
(defrule regula2
(or( Punkty (  pkt 8)) ( Punkty (  pkt 9)) ( Punkty (  pkt 10 )) ( Punkty (  pkt 11)) ( Punkty (  pkt 12)))
=>
 (printout t "eeeee" crlf)
)
(facts)
(run)