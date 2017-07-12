package com.sapient.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;



@Entity
public class Fill implements Serializable {

                /**
	 * 
	 */
	

				@Id
                @Column(name = "Execution_ID", nullable = false)
                @GeneratedValue(strategy = GenerationType.AUTO )
                public long executionId;

                @Column(name = "Block_ID", nullable = false)
                public double blockId;

                @Column(name = "Side", nullable = false)
                public String side;

                @Column(name = "Symbol", nullable = false)
                public String symbol;

                @Column(name = "Execution_TimeStamp", nullable = false)
                public String timestamp;

                @Column(name = "Execution_Price", nullable = false)
                public double executionPrice;

                @Column(name = "Executed_Quantity", nullable = false)
                public long executedQuantity;

                @Column(name = "Open_Quantity", nullable = false)
                public long openQuantity;
                
//            @ManyToOne
//            private Block block;
//
//            public Block getBlock() {
//                            return block;
//            }
//
//            public void setBlock(Block block) {
//                            this.block = block;
//            }

                public double getBlock_id() {
                                return blockId;
                }

                public long getExecutionId() {
                                return executionId;
                }

                

                public double getBlockId() {
                                return blockId;
                }

                public void setBlockId(double blockId) {
                                this.blockId = blockId;
                }

                public String getSide() {
                                return side;
                }

                public void setSide(String side) {
                                this.side = side;
                }

                public String getSymbol() {
                                return symbol;
                }

                public void setSymbol(String symbol) {
                                this.symbol = symbol;
                }

                public String getTimestamp() {
                                return timestamp;
                }

                public void setTimestamp(String timestamp) {
                                this.timestamp = timestamp;
                }

                public double getExecutionPrice() {
                                return executionPrice;
                }

                public void setExecutionPrice(double executionPrice) {
                                this.executionPrice = executionPrice;
                }

                public double getExecutedQuantity() {
                                return executedQuantity;
                }

                public void setExecutedQuantity(long executedQuantity) {
                                this.executedQuantity = executedQuantity;
                }

                public double getOpenQuantity() {
                                return openQuantity;
                }

                public void setOpenQuantity(long openQuantity) {
                                this.openQuantity = openQuantity;
                }

}
