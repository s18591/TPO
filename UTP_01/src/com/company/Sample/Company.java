package com.company.Sample;

import com.company.Interfaces.IAggregable;
import com.company.Interfaces.IDeeplyCloneable;

public class Company implements IAggregable<Company,Double>, IDeeplyCloneable<Company> {

    private double share;

    public Company() {
    }


    public Company(double share) {this.share = share;}

    public double getShare() {
        return share;
    }

    @Override
    public Double aggregate(Double intermediateResult) {
        if(intermediateResult == null){
            return share;
        }
        intermediateResult++;
        return share + intermediateResult;
    }

    @Override
    public Company deepClone() {
        Company company = new Company();
        company.share = getShare();
        return company;
    }
}
