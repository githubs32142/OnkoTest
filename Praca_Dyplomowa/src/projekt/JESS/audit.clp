(clear)
(reset)

(deftemplate Punkty
(slot pkt )
)

(defrule regula1
 (or( Punkty (  pkt 2))( Punkty (  pkt 1)))
=>
 (printout t "czu�o�� 53-92%, swowodno�� 81-95%" crlf)
)
(defrule regula2
(or( Punkty (  pkt 8)) ( Punkty (  pkt 9)) ( Punkty (  pkt 10 )) ( Punkty (  pkt 11)) ( Punkty (  pkt 12)))
=>
 (printout t "eeeee" crlf)
)
(facts)
(run)