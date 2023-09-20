--------------------------- MODULE BlockingQueue ---------------------------
(***************************************************************************)
(* Original problem and spec by Michel Charpentier                         *)
(* http://www.cs.unh.edu/~charpov/programming-tlabuffer.html               *)
(***************************************************************************)
EXTENDS Naturals, Sequences

CONSTANTS Producers,   (* the (nonempty) set of producers                       *)
          Consumers,   (* the (nonempty) set of consumers                       *)
          BufCapacity  (* the maximum number of messages in the bounded buffer  *)

ASSUME Assumption ==
       /\ Producers # {}                      (* at least one producer *)
       /\ Consumers # {}                      (* at least one consumer *)
       /\ Producers \intersect Consumers = {} (* no thread is both consumer and producer *)
       /\ BufCapacity \in (Nat \ {0})         (* buffer capacity is at least 1 *)

-----------------------------------------------------------------------------
(*
--algorithm BlockingQueue {
    variables buffer = << >>, waitP = {}, waitC = {};

    define {
        isfull(b) == Len(b) = BufCapacity
        isempty(b) == Len(b) = 0
    }

    macro wait(id) {
        if(id \in Producers) {
            waitP := waitP \union {id};
        }
        else {
            waitC := waitC \union {id};
        }
    }

    macro notifyC() {
        if (waitC # {}) {
            with (t \in waitC) {
                waitC := waitC \ {t};
            }
        }
    }

    macro notifyP() {
        if (waitP # {}) {
            with (t \in waitP) {
                waitP := waitP \ {t};
            }
        }
    }

    macro put(id) {
        if (isfull(buffer)) {
            wait(id);
        }
        else {
            buffer := Append(buffer, id);
            notifyC();
        }
    }

    macro get(item) {
        if (isempty(buffer)) {
            wait(item);
        }
        else {
            buffer := Tail(buffer);
            notifyP();
        }
    }
    

    process (p \in Producers) {
p0:     while (TRUE) {
            await(self \notin waitP);
            put(self);
        }
    }

    process (c \in Consumers) {
c0:     while (TRUE) {
            await(self \notin waitC);
            get(self);
       }
    }
}
*)
\* BEGIN TRANSLATION (chksum(pcal) = "f5c8e8d0" /\ chksum(tla) = "191da49c")
VARIABLES buffer, waitP, waitC

(* define statement *)
isfull(b) == Len(b) = BufCapacity
isempty(b) == Len(b) = 0


vars == << buffer, waitP, waitC >>

ProcSet == (Producers) \cup (Consumers)

Init == (* Global variables *)
        /\ buffer = << >>
        /\ waitP = {}
        /\ waitC = {}

p(self) == /\ (self \notin waitP)
           /\ IF isfull(buffer)
                 THEN /\ IF self \in Producers
                            THEN /\ waitP' = (waitP \union {self})
                                 /\ waitC' = waitC
                            ELSE /\ waitC' = (waitC \union {self})
                                 /\ waitP' = waitP
                      /\ UNCHANGED buffer
                 ELSE /\ buffer' = Append(buffer, self)
                      /\ IF waitC # {}
                            THEN /\ \E t \in waitC:
                                      waitC' = waitC \ {t}
                            ELSE /\ TRUE
                                 /\ waitC' = waitC
                      /\ waitP' = waitP

c(self) == /\ (self \notin waitC)
           /\ IF isempty(buffer)
                 THEN /\ IF self \in Producers
                            THEN /\ waitP' = (waitP \union {self})
                                 /\ waitC' = waitC
                            ELSE /\ waitC' = (waitC \union {self})
                                 /\ waitP' = waitP
                      /\ UNCHANGED buffer
                 ELSE /\ buffer' = Tail(buffer)
                      /\ IF waitP # {}
                            THEN /\ \E t \in waitP:
                                      waitP' = waitP \ {t}
                            ELSE /\ TRUE
                                 /\ waitP' = waitP
                      /\ waitC' = waitC

Next == (\E self \in Producers: p(self))
           \/ (\E self \in Consumers: c(self))

Spec == Init /\ [][Next]_vars

\* END TRANSLATION 

=============================================================================
