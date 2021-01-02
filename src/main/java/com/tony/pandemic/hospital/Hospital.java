package com.tony.pandemic.hospital;

import com.tony.pandemic.item.Item;
import com.tony.pandemic.localization.Localization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Hospital implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String address;

    private String cnpj;

    @OneToOne(cascade = CascadeType.ALL)
    private Localization localization;

    private double percentageOfOccupation;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> itens = new ArrayList<>();


}
