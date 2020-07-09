package com.example.demo.util;

import com.intelligt.modbus.jlibmodbus.Modbus;
import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusNumberException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusProtocolException;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.intelligt.modbus.jlibmodbus.master.ModbusMasterFactory;
import com.intelligt.modbus.jlibmodbus.tcp.TcpParameters;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ModbusTcp {
    public static void getModbusValue(){
        try {
            //设置tcp的参数
            TcpParameters tcpParameters = new TcpParameters();
            //设置ip地址
            InetAddress address = InetAddress.getByName("192.168.1.5");
            tcpParameters.setHost(address);
            //tcp设置长链接和参数
            tcpParameters.setKeepAlive(true);
            tcpParameters.setPort(Modbus.TCP_PORT);
            //创建一个主机
            ModbusMaster master = ModbusMasterFactory.createModbusMasterTCP(tcpParameters);
            Modbus.setAutoIncrementTransactionId(true);
            int slaveId = 1; //从机地址
            int offset = 0;//寄存器开始地址
            int quantity = 8; //寄存器读取长度
            try {
                if (!master.isConnected()){
                    master.connect();
                }
                int[] values = master.readInputRegisters(slaveId, offset, quantity);
                for (int result: values
                     ) {
                    System.out.println(offset+":"+result);
                }
            } catch (ModbusIOException e) {
                e.printStackTrace();
            } catch (ModbusNumberException e) {
                e.printStackTrace();
            } catch (ModbusProtocolException e) {
                e.printStackTrace();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ModbusTcp.getModbusValue();
    }
}
